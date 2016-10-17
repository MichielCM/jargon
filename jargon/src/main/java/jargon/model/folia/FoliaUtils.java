package jargon.model.folia;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathNotFoundException;
import org.apache.commons.lang.ArrayUtils;

import jargon.core.Console;

public final class FoliaUtils {
	
	@SuppressWarnings("unchecked")
	public static ArrayList<W> getWords(FoLiA folia) {
		return (ArrayList<W>) JXPathContext.newContext(folia).getValue("text/p/s/w");
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Dependency> getDependencies(FoLiA folia) {
		return (ArrayList<Dependency>) JXPathContext.newContext(folia).getValue("text/p/s/dependencies/dependency");
	}
	
	public static boolean matches(Object parent, String xPathQuery, String match) {
		try {
			return ((String)JXPathContext.newContext(parent).getValue(xPathQuery)).equals(match);
		} catch(JXPathNotFoundException e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String getValue(Object parent, String xPathQuery) {
		//try {
			Console.log(JXPathContext.newContext(parent).getValue(xPathQuery));
		
		return ((ArrayList<String>)JXPathContext.newContext(parent).getValue(xPathQuery)).get(0);
		/*} catch(JXPathNotFoundException e) {
			return null;
		}*/
	}
	
/*((String)folia.getText().get(0).getP().get(0).getS().get(0).getT().get(0).getContent().get(0)).substring(
((String)folia.getText().get(0).getP().get(0).getS().get(0).getT().get(0).getContent().get(0)).indexOf(
(String)signifier.getT().get(0).getContent().get(0)
)
)*/
	
	@SuppressWarnings("unchecked")
	public static W[] getDescendantsByLemma(JXPathContext context, W parent, String lemma, String exclusionQuery, ArrayList<W> matches) {
		Iterator<Dependency> iterator = context.iterate("text/p/s/dependencies/dependency/hd/wref[@idAttribute='".concat(parent.id).concat("']/../.."));
		
		while (iterator.hasNext()) {
			Dependency dependency = iterator.next();
			W w = (W) context.getValue("text/p/s/w[@id='".concat(dependency.dep.get(0).wref.get(0).idAttribute).concat("']"));
			
			if (w.lemma.get(0).clazz.equals(lemma))
				matches.add(w);
			
			JXPathContext wContext = JXPathContext.newContext(w);
			wContext.setLenient(true);
			
			if (exclusionQuery == null || wContext.getValue(exclusionQuery) == null)
				getDescendantsByLemma(context, w, lemma, exclusionQuery, matches);
		}
		
		return matches.toArray(new W[matches.size()]);
	}
	
	public static W[] getDescendantsByLemma(FoLiA folia, W parent, String lemma) {
		return getDescendantsByLemma(
			JXPathContext.newContext(folia),
			parent,
			lemma,
			null,
			new ArrayList<W>()
		);
	}
	
	public static W[] getDescendantsByLemma(FoLiA folia, W parent, String lemma, String exclusionQuery) {
		return getDescendantsByLemma(
			JXPathContext.newContext(folia),
			parent,
			lemma,
			exclusionQuery,
			new ArrayList<W>()
		);
	}
	
	public static W[] getDescendantsByLemmata(FoLiA folia, W parent, String... lemmata) {
		W[] returnW = null;
		
		for (String lemma : lemmata) {
			W[] w = getDescendantsByLemma(folia, parent, lemma, null);
			
			if (w.length == 0)
				return null;
			else
				returnW = (W[])ArrayUtils.addAll(returnW, w);
		}
		
		return returnW;
	}
	
	@SuppressWarnings("unchecked")
	public static W[] getDescendantsByDependency(JXPathContext context, W parent, String dependencyType, String exclusionQuery, ArrayList<W> matches) {
		Console.log(dependencyType);
		Console.log(context.getValue("text/p/s/dependencies/dependency"));
		Console.log(context.getValue("text/p/s/dependencies/dependency[clazz='".concat(dependencyType).concat("']")));
		
		Iterator<Dependency> iterator = context.iterate("text/p/s/dependencies/dependency[clazz='".concat(dependencyType).concat("']/hd/wref[@idAttribute='".concat(parent.id).concat("']/../..")));
		
		while (iterator.hasNext()) {
			Dependency dependency = iterator.next();
			W w = (W) context.getValue("text/p/s/w[@id='".concat(dependency.dep.get(0).wref.get(0).idAttribute).concat("']"));
			matches.add(w);
			
			JXPathContext wContext = JXPathContext.newContext(w);
			wContext.setLenient(true);
			
			if (exclusionQuery == null || wContext.getValue(exclusionQuery) == null)
				getDescendantsByLemma(context, w, dependencyType, exclusionQuery, matches);
		}
		
		return matches.toArray(new W[matches.size()]);
	}
	
	public static W[] getDescendantsByDependency(FoLiA folia, W parent, String dependency, String exclusionQuery) {
		return getDescendantsByDependency(
			JXPathContext.newContext(folia),
			parent,
			dependency,
			exclusionQuery,
			new ArrayList<W>()
		);
	}
	
	public static W getAncestor(FoLiA folia, W descendant, String criterion) {
		JXPathContext context = JXPathContext.newContext(folia);
		context.setLenient(true);
		
		while(
			context.getValue("//dependency/dep/wref[@idAttribute='".concat(descendant.getId()).concat("']")) != null
		) {
			W w = (W) context.getValue("text/p/s/w[@id='".concat(
				(String)context.getValue("//dependency/dep/wref[@idAttribute='".concat(
					descendant.getId()
				).concat("']/../../hd/wref/@idAttribute"))
			).concat("']"));
			
			JXPathContext wordContext = JXPathContext.newContext(w);
			wordContext.setLenient(true);
			
			if (wordContext.getValue(criterion) != null)
				return w;
			/*else if (w.getId().equals(criterion))
				return w;*/
			else
				descendant = w;
		}
		
		return null;
	}
	
	public static boolean isAncestor(FoLiA folia, W descendant, W ancestor) {
		return (getAncestor(
			folia,
			descendant,
			//ancestor.getId()
			"@id='".concat(ancestor.getId()).concat("'")
		) != null);
	}
	
	public static W addSense(W w, String clazz, Feat... feats) {
		Sense sense = new Sense(clazz);
		
		for (Feat feat : feats) {
			sense.getFeat().add(feat);
		}
		
		w.getSense().add(sense);
		
		return w;
	}
	
	public static Object addToList(Object returnObj, java.util.List<Object> list, Object... objects) {
		for (Object object : objects)
			list.add(object);
		
		return returnObj;
	}
	
}
