package jargon.utils.autocorrector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.languagetool.language.Dutch;
import org.languagetool.rules.patterns.PatternRule;
import org.languagetool.rules.patterns.PatternToken;

import jargon.utils.ArrayTools;

public class AbbreviationReplaceRule extends PatternRule {

	@SuppressWarnings("unchecked")
	public AbbreviationReplaceRule(String abbreviation, String description, boolean endsWithPeriod) {
		super(
			abbreviation,
			new Dutch(),
			(List<PatternToken>) ArrayTools.clean(
				new ArrayList<PatternToken>(
					Arrays.asList(
						new PatternToken[] {
							new PatternToken("(".concat(abbreviation).concat(")(-.*)?"), false, true, false),
							(endsWithPeriod ? new PatternToken(".", false, false, false) : null)
							//(PatternToken) Anonymous.run(new PatternToken(".", false, false, false), "setMinOccurrence", new Object[] {0})
						}
					)
				)
			),
			"", "<suggestion><match no=\"1\" regexp_match=\"(".concat(abbreviation).concat(")(-.*)?\" regexp_replace=\"$1\"/></suggestion>"), ""
			//"", "<suggestion>".concat(description).concat("</suggestion>"), ""
		);
	}
	
	
	
}
