package jargon.model.folia;

import java.util.ArrayList;

import org.apache.commons.jxpath.JXPathContext;

public final class FoliaUtils {

	@SuppressWarnings("unchecked")
	public static ArrayList<W> getWords(FoLiA folia) {
		return (ArrayList<W>) JXPathContext.newContext(folia).getValue("text/p/s/w");
		
		/*ArrayList<W> words = new ArrayList<W>();
		
		for (P p : (List<P>) JAXBUtils.getElementsByType(text.getSentimentsOrEntitiesOrMorphology(), P.class)) {
			for (S s : (List<S>) JAXBUtils.getElementsByType(p.getSentimentsOrEntitiesOrMorphology(), S.class)) {
				for (W w : (List<W>) JAXBUtils.getElementsByType(s.getSentimentsOrEntitiesOrMorphology(), W.class)) {
					words.add(w);
				}
			}
		}
		
		return words.toArray(new W[words.size()]);*/
	}
	
}
