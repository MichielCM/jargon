package jargon.model.folia;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.lang.ArrayUtils;

import jargon.core.Console;

public final class FoliaUtils {
	
	//@SuppressWarnings("unchecked")
	public static ArrayList<W> getWords(FoLiA folia) {
		ArrayList<W> words = new ArrayList<W>();
		
		for (Text text : folia.getText()) {
			for (P p : text.getP()) {
				for (S s : p.getS()) {
					for (W w : s.getW()) {
						Console.log(w.getT().get(0).getContent().get(0));
						words.add(w);
					}
				}
			}
		}
		
		return words;
		//return (ArrayList<W>) JXPathContext.newContext(folia).getValue("text/p/s/w");
	}
	
	//@SuppressWarnings("unchecked")
	public static ArrayList<Dependency> getDependencies(FoLiA folia) {
		ArrayList<Dependency> wordDependencies = new ArrayList<Dependency>();
		
		for (Text text : folia.getText()) {
			for (P p : text.getP()) {
				for (S s : p.getS()) {
					for (Dependencies dependencies : s.getDependencies()) {
						for (Dependency dependency : dependencies.getDependency()) {
							wordDependencies.add(dependency);
						}
					}
				}
			}
		}
		
		return wordDependencies;
		//return (ArrayList<Dependency>) JXPathContext.newContext(folia).getValue("text/p/s/dependencies/dependency");
	}
	
	public static boolean equals(Object parent, String xPathQuery, String match) {
		//Console.log(parent, xPathQuery, match);
		try {
			JXPathContext context = JXPathContext.newContext(parent);
			context.setLenient(true);
			return ((String)context.getValue(xPathQuery)).equals(match);
		} catch(NullPointerException e) {
			return false;
		}
	}
	
	public static boolean matches(Object parent, String xPathQuery, String match) {
		//Console.log(parent, xPathQuery, match);
		try {
			JXPathContext context = JXPathContext.newContext(parent);
			context.setLenient(true);
			return ((String)context.getValue(xPathQuery)).matches(match);
		} catch(NullPointerException e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Object getValue(Object parent, String xPathQuery) {
		//Console.log(JXPathContext.newContext(parent).getValue(xPathQuery));
		JXPathContext context = JXPathContext.newContext(parent);
		context.setLenient(true);
		try {
			return ((ArrayList<String>)context.getValue(xPathQuery)).get(0);
		} catch(ClassCastException | NullPointerException e) {
			try {
				return (String)context.getValue(xPathQuery);
			} catch(ClassCastException f) {
				return (Object)context.getValue(xPathQuery);
			}
		}
	}
	
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
	
	public static W getPrevious(FoLiA folia, W w) {
		JXPathContext context = JXPathContext.newContext(folia);
		context.setLenient(true);
		//Console.log(context.getValue("//w[id='".concat(w.getId()).concat("']/preceding-sibling::w[1]/lemma/@clazz")));
		return (W)context.getValue("//w[id='".concat(w.getId()).concat("']/preceding-sibling::w[1]"));
	}
	
	public static W getPrevious(FoLiA folia, W w, String selector, String excluder) {
		while (getValue(w, selector) == null) {
			w = getPrevious(folia, w);
			
			if (w == null || getValue(w, excluder) != null) {
				w = null;
				break;
			}
		}
		
		return w;
	}
	
	public static W getNext(FoLiA folia, W w) {
		JXPathContext context = JXPathContext.newContext(folia);
		context.setLenient(true);
		//Console.log(context.getValue("//w[id='".concat(w.getId()).concat("']/following-sibling::w[1]/lemma/@clazz")));
		return (W)context.getValue("//w[id='".concat(w.getId()).concat("']/following-sibling::w[1]"));
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
