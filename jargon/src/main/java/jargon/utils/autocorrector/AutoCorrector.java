package jargon.utils.autocorrector;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
/*import org.languagetool.JLanguageTool;
import org.languagetool.language.Dutch;
import org.languagetool.rules.CategoryId;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;*/

import jargon.core.Console;
import jargon.core.Singleton;
import jargon.utils.csvreader.CSVReader;
import jargon.utils.csvreader.CSVRecord;

public class AutoCorrector {

	//private JLanguageTool jLanguageTool;
	
	public AutoCorrector() {
		//this.jLanguageTool = new JLanguageTool(new Dutch());
		//this.disableAllCategories();
	}
	
	/*public void addCustomRules(Rule... rules) {
		for (Rule rule : rules) {
			this.jLanguageTool.addRule(rule);
		}
	}*/
	
	/*private void disableAllCategories() {
		for (Rule rule : jLanguageTool.getAllActiveRules()) {
			//Console.log(rule.getCategory().getId());
			this.jLanguageTool.disableCategory(rule.getCategory().getId());
		}
	}*/
	
	/** Replaces regular expressions in text string specified by categories.
	 * @param text				String to check.
	 * @param categories		Array of categories that will be checked. Files will be loaded as CSV files from the resource folder.
	 * @return					Corrected string.
	 */
	public String autoCorrectRegEx(String text, String... categories) {
		for (String category : categories) {
			CSVReader csvReader = new CSVReader(
				new File(
					this.getClass().getClassLoader().getResource(
						category.concat(".csv")
					).getFile()
				),
				new String[] { "id", "find", "replace"}, "id", System.getProperty("line.separator"), "\t"
			);
			
			for (CSVRecord csvRecord : csvReader.read()) {
				text = text.replaceAll(csvRecord.get("find"), csvRecord.get("replace"));
			}
		}
		
		return text;
	}
	
	/** Replaces words that do not appear in the database tables specified by their closest match.
	 * @param text			Text to be corrected.
	 * @param resources		Database tables containing correct tokens.
	 * @return				Corrected text.
	 */
	public String autoCorrect(String text, String... resources) {
		//prepare query
		ArrayList<String> queries = new ArrayList<String>();
		
		for (String resource : resources) {
			queries.add("SELECT `token` FROM `spellcheck_".concat(resource).concat("` WHERE `token` LIKE ?"));
		}
		
		String query = StringUtils.join(
			queries.toArray(new String[queries.size()]),
			" UNION "
		);
		
		String[] parts = text.split("\\b");
		ArrayList<String> correctedParts = new ArrayList<String>();
		
		for (String part : parts) {
			if (!part.matches("[ \\t.,—?!;\\n]+") && !this.hasUnusualCasing(part)) {
				//check if terms exist in databases
				String[] partAsParameters = new String[queries.size()];
				Arrays.fill(partAsParameters, part);
				
				String[] results = (String[]) Singleton.getInstance().getSQLManager().queryPreparedAsPrimitives(
					query, partAsParameters, "java.lang.String"
				);
				
				if (results.length == 0) {
					//if term does not exist, find closest match
					ArrayList<String> subQueries = new ArrayList<String>();
					ArrayList<String> parameters = new ArrayList<String>();
					
					for (String resource : resources) {
						subQueries.add("(SELECT `token` FROM `spellcheck_".concat(resource).concat("` WHERE `token` LIKE ? OR `token` LIKE ? HAVING jaro_winkler_similarity(?,`token`) > .9)"));
						parameters.add("%".concat(part.substring((part.length() / 2))));
						parameters.add(part.substring(0, (part.length() / 2)).concat("%"));
						parameters.add(part);
					}
					
					String subQuery = StringUtils.join(
						subQueries.toArray(new String[queries.size()]),
						" UNION "
					).concat(" ORDER BY jaro_winkler_similarity(?,`token`) DESC");
					
					parameters.add(part);
					
					String[] corrections = (String[]) Singleton.getInstance().getSQLManager().queryPreparedAsPrimitives(
						subQuery, parameters.toArray(new String[parameters.size()]), "java.lang.String"
					);
					
					if (corrections.length > 0) {
						part = corrections[0];
					}
				}
			}
			
			correctedParts.add(part);
		}
		
		return StringUtils.join(correctedParts.toArray(new String[correctedParts.size()]), "");
	}
	
	/** Runs JLanguageTool.check on a string and automatically incorporates (first) suggestions.
	 * @param text				String to check.
	 * @param categories		Array of categories that will be checked. Custom rules have category MISC.
	 * @return					Corrected string.
	 */
	/*public String autoCorrect(String text, String... categories) {
		try {
			for (String category : categories) {
				this.jLanguageTool.enableRuleCategory(new CategoryId(category));
			}
			
			List<RuleMatch> matches = jLanguageTool.check(text);
			int sizeCorrection = 0;
			
			for (RuleMatch match : matches) {
				Console.log(match.getRule().getId(), match.getMessage());
				
				if (!this.hasUnusualCasing(text)) {
					if (match.getSuggestedReplacements().size() > 0) {
						Console.log(match.getSuggestedReplacements().get(0));
						text = text.substring(0, match.getFromPos() + sizeCorrection).concat(
							match.getSuggestedReplacements().get(0)
						).concat(
							text.substring(match.getToPos() + sizeCorrection)
						);
						sizeCorrection = sizeCorrection + (match.getSuggestedReplacements().get(0).length() - (match.getToPos() - match.getFromPos()));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return text;
	}*/
	
	/** Returns whether or not a string has a mix of lower- and uppercased characters. test returns false, Test returns false, TEST returns true, TeSt returns true.
	 * @param term		String to check.
	 * @return			Whether or not term is unusually cased.
	 */
	private boolean hasUnusualCasing(String term) {
		int uppercase = 0;
		
		for (char c : term.toCharArray()) {
			if (Character.isUpperCase(c) || Character.isTitleCase(c))
				uppercase ++;
		}
		
		if (uppercase > 1) {
			return true;
		} else if (uppercase == 1) {
			if (Character.isUpperCase(term.substring(0,1).toCharArray()[0])
			|| Character.isTitleCase(term.substring(0,1).toCharArray()[0])) {
				return false;
			} else return true;
		} else return false;
	}
	
}
