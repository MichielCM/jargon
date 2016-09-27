package jargon.utils.autocorrector;

import java.io.IOException;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Dutch;
import org.languagetool.rules.CategoryId;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;

import jargon.core.Console;

public class AutoCorrector {

	private JLanguageTool jLanguageTool;
	
	public AutoCorrector() {
		this.jLanguageTool = new JLanguageTool(new Dutch());
		this.disableAllCategories();
	}
	
	public void addCustomRules(Rule... rules) {
		for (Rule rule : rules) {
			this.jLanguageTool.addRule(rule);
		}
	}
	
	private void disableAllCategories() {
		for (Rule rule : jLanguageTool.getAllActiveRules()) {
			//Console.log(rule.getCategory().getId());
			this.jLanguageTool.disableCategory(rule.getCategory().getId());
		}
	}
	
	/** Runs JLanguageTool.check on a string and automatically incorporates (first) suggestions.
	 * @param text				String to check.
	 * @param categories		Array of categories that will be checked. Custom rules have category MISC.
	 * @return					Corrected string.
	 */
	public String autoCorrect(String text, String... categories) {
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
	}
	
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
