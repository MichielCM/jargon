package jargon.utils;

import java.util.Arrays;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Dutch;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.patterns.PatternRule;
import org.languagetool.rules.patterns.PatternToken;

import jargon.core.Console;
import jargon.utils.fuzzymatcher.Record;
import jargon.utils.fuzzymatcher.Resource;

public class LanguageToolWrapper {

	private JLanguageTool jLanguageTool;
	
	public LanguageToolWrapper() {
		jLanguageTool = new JLanguageTool(new Dutch());
	}
	
	public void addCustomCorrector(Resource resource) {
		/*for (Record record : resource.read()) {
			PatternRule rule = new PatternRule(
				"TESTID",
				new Dutch(),
				Arrays.asList(new PatternToken[] {
					new PatternToken(record.getId(), false, false, false)
				}), "", "<suggestion>".concat(record.getValue()).concat("</suggestion>"), ""
			);
		}*/
		
		
	}
	
	public String autoCorrect(String text) {
		/*List<RuleMatch> matches = jLanguageTool.check(text);
		
		for (RuleMatch match : matches) {
			/*switch(match.getRule().getCategory().getId().toString()) {
				case "TYPOS":
				case "AFKORTINGEN":*/
			/*Console.log(match.getRule().getId());
			Console.log(match.getMessage());
			Console.log(match.getSuggestedReplacements());
					//Console.log(match.getRule().getClass().getName());
					
					//if (match.getRule().getClass().getName().equals("org.languagetool.rules.patterns.PatternRule"))
						//Console.log(((PatternRule)match.getRule()).getSuggestionsOutMsg());
					/*if (match.getSuggestedReplacements().size() > 0) {
						segments[i] = segments[i].substring(0, match.getFromPos()).concat(
							match.getSuggestedReplacements().get(0)
						).concat(
							segments[i].substring(match.getToPos())
						);
					}*/
					//break;
			//}
		//}
		
		return text;
	}
	
}
