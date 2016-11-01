package jargon.model.folia;

import static java.util.Comparator.comparing;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.lang3.StringUtils;

import jargon.core.Console;

public class FoliaWrapper {

	private JXPathContext foliaContext;
	private JXPathContext objectContext;
	private Object object;
	
	public FoliaWrapper() {}
	
	public FoliaWrapper(FoLiA folia) {
		this(folia, JXPathContext.newContext(folia));
	}
	
	public FoliaWrapper(Object object, JXPathContext foliaContext) {
		this.object = object;
		this.foliaContext = foliaContext;
		this.foliaContext.setLenient(true);
		this.objectContext = JXPathContext.newContext(object);
		this.objectContext.setLenient(true);
	}
	
	/** Returns root FoLiA element (instantiated through foliaContext).
	 * @return	FoliaWrapper containing FoLiA root element as object.
	 */
	public FoliaWrapper folia() {
		if (this.foliaContext == this.objectContext) {
			return this;
		} else {
			return new FoliaWrapper(
				this.foliaContext.getContextBean(),
				this.foliaContext
			);
		}
	}
	
	public void set(Object object) {
		this.object = object;
		
		if (object != null) {
			this.objectContext = JXPathContext.newContext(object);
			this.objectContext.setLenient(true);
		}
	}
	
	public Object get() {
		return this.object;
	}
	
	public String id() {
		return this.val("@id").getAsString();
	}
	
	@SuppressWarnings("unchecked")
	public String getAsString() {
		try {
			return ((java.util.List<String>) this.object).get(0);
		} catch(ClassCastException e) {
			return this.object.toString();
		}
	}
	
	public boolean isNull() {
		return this.notExists();
	}
	
	public boolean notExists() {
		if (this.object == null)
			return true;
		else if (this.object.getClass().isArray())
			return ((Object[])this.object).length == 0;
		else
			return false;
	}
	
	public boolean exists() {
		return !this.notExists();
	}
	
	/* Returns true if this.object matches comparator. If comparator is FoliaWrapper instance, its object will be used.
	 * @param comparator	Object to which this.object will be compared.
	 * @return				True if comparator is equal to this.object
	 */
	@Override
	public boolean equals(Object comparator) {
		try {
			if (comparator instanceof FoliaWrapper)
				return this.object.equals(((FoliaWrapper)comparator).get());
			else
				return this.object.equals(comparator);
		} catch(NullPointerException e) {
			return false;
		}
	}
	
	/** Return true if selector run on object is equal to comparator.
	 * @param selector		Query relative to object.
	 * @param comparator	Object to which query result is compared.
	 * @return				True if comparator is equal to query's result.
	 */
	public boolean equals(String selector, Object comparator) {
		try {
			return this.objectContext.getValue(selector).equals(comparator);
		} catch(NullPointerException e) {
			//Console.log("matches", selector, comparator, e.toString());
			return false;
		}
	}
	
	/** Return true if selector run on object is equal to comparator regex.
	 * @param selector		Query relative to object.
	 * @param comparator	RegEx string to which query result is compared.
	 * @return				True if comparator is equal to query's result.
	 */
	public boolean matches(String selector, String comparator) {
		try {
			return ((String)this.objectContext.getValue(selector)).matches(comparator);
		} catch(NullPointerException e) {
			//Console.log("matches", selector, comparator, e.toString());
			return false;
		}
	}
	
	/** Returns node wrapped in FoliaWrapper class. Ideal for chaining.
	 * @param identifiedNode	Node to be wrapped.
	 * @return					FoliaWrapper object containing identifiedNode.
	 */
	public FoliaWrapper node(Object identifiedNode) {
		//Console.log(identifiedNode);
		return new FoliaWrapper(identifiedNode, this.foliaContext);
		
		/*String id = null;
		
		try {
			id = (String) identifiedNode.getClass().getMethod("getId").invoke(identifiedNode);
		} catch(NoSuchMethodException e) {
			try {
				id = (String) identifiedNode.getClass().getMethod("getIdAttribute").invoke(identifiedNode);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException f) {
				Console.log(identifiedNode, f.getMessage());
				return new FoliaWrapper();
			}
		} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			Console.log(identifiedNode, e.toString());
			return new FoliaWrapper();
		}
		
		if (this.nodes == null)
			return this.val("//w[id='".concat(id).concat("']"));
		else if (!this.nodes.containsKey(id))
			this.nodes.put(id, this.val("//w[id='".concat(id).concat("']")));
		
		return this.nodes.get(id);*/
	}
	
	public FoliaWrapper log() {
		if (this.object == null) {
			Console.log("nullObject");
		} else {
			Console.log(this.object.toString(), (this.object.getClass().isArray() ? ((Object[])this.object).length : null));
			Console.log(this.val("lemma/@clazz").getAsString());
		}
		
		return this;
	}
	
	public FoliaWrapper log(Object o) {
		Console.log(this.object.toString());
		Console.log(this.val("t/content"));
		
		Console.log(o.toString(), o.getClass().getName());
		Console.log(this.node(o).val("t/content"));
		
		return this;
	}
	
	/*public String phrase(String selector, String prevSelector, String nextSelector) {
		ArrayList<String> values = new ArrayList<String>();
		FoliaWrapper first = this.prev(prevSelector);
		FoliaWrapper last = this.next(nextSelector);
		FoliaWrapper current = first;
		
		while (last.get() != current.get()) {
			current = current.next();
			values.add(current.val(selector).getAsString());
		}
		
		return StringUtils.join(
			values.toArray(new String[values.size()]),
			" "
		);
	}*/
	
	/** Returns a string containing the phrase relative to this.object, i.e. the word and all its descendants in correct order, separated by a space.
	 * @param selector		Query relative to object.
	 * @return				String containing query result on all descendants of this.object, concatenated and separated by a space.
	 */
	public String phrase(String selector) {
		if (this.exists()) {
			ArrayList<FoliaWrapper> children = this.children(true);
			children.add(this);
			
			Collections.sort(children, comparing(FoliaWrapper::id));
			
			String[] phrase = children.stream().map(
				fw -> fw.val(selector).getAsString()
			).collect(
				Collectors.toList()
			).toArray(
				new String[children.size()]
			);
			
			return StringUtils.join(
				phrase, " "
			);
		} else return null;
	}
	
	/** Returns value returned by selector on object.
	 * @param selector		Query relative to object.
	 * @return				FoliaWrapper containing query result as object.
	 */
	public FoliaWrapper val(String selector) {
		try {
			return new FoliaWrapper(
				this.objectContext.getValue(selector),
				this.foliaContext
			);
		} catch(NullPointerException e) {
			//Console.log("val", selector, e.toString());
			return new FoliaWrapper();
		}
	}
	
	/** Sets attribute value to specified value. Note: this function does not work on objects, use add() instead.
	 * @param selector		Query relative to object.
	 * @param value			Value to which attribute is set.
	 * @return
	 */
	public FoliaWrapper val(String selector, String value) {
		this.objectContext.createPathAndSetValue(selector, value);
		return this;
	}
	
	/** Adds new object to current object, returns new object.
	 * @param newObject		Object to be added.
	 * @return				FoliaWrapper containing newObject as object.
	 */
	@SuppressWarnings("unchecked")
	public FoliaWrapper add(Object newObject) {
		try {
			java.util.List<Object> objectList = (java.util.List<Object>) this.object.getClass().getDeclaredMethod(
				"get".concat(newObject.getClass().getSimpleName())
			).invoke(this.object);
			
			objectList.add(newObject);
			
			return new FoliaWrapper(
				newObject,
				this.foliaContext
			);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/** Returns next or previous sibling relative to currently selected node.
	 * @param previous	Boolean indicating whether the previous (true) or next (false) word will be returned.
	 * @return			FoliaWrapper containing sibling as object.
	 */
	public FoliaWrapper sibling(boolean previous) {
		FoliaWrapper foliaWrapper = null;
		
		if (previous)
			foliaWrapper = this.prev();
		else
			foliaWrapper = this.next();
		
		if (foliaWrapper.isNull() && previous)
			return this.next();
		else if (foliaWrapper.isNull() && !previous)
			return this.prev();
		
		return foliaWrapper;
	}
	
	/** Select nearest sibling (previous or next word) relative to selector
	 * @param selector	Query to which sibling object will be matched.
	 * @return			FoliaWrapper containing sibling as object.
	 */
	public FoliaWrapper sibling(String selector) {
		return this.sibling(selector, null);
	}
	
	/** Select nearest sibling (previous or next word) relative to selector, until excludor is met.
	 * @param selector	Query to which previous object will be matched.
	 * @param excludor	Query through which sibling object will be excluded.
	 * @return			FoliaWrapper containing sibling as object.
	 */
	public FoliaWrapper sibling(String selector, String excluder) {
		FoliaWrapper foliaWrapperPrev = this.prev(selector, excluder);
		
		if (foliaWrapperPrev.notExists())
			return this.next(selector, excluder);
		else {
			FoliaWrapper foliaWrapperNext = this.next(selector, excluder);
			
			if (foliaWrapperNext.exists()) {
				if (
					((Double)this.foliaContext.getValue("count(//w[id='".concat(((W)this.object).getId()).concat("']/preceding-sibling::*)"))
					- (Double)this.foliaContext.getValue("count(//w[id='".concat(((W)foliaWrapperPrev.get()).getId()).concat("']/preceding-sibling::*)")))
				  > ((Double)this.foliaContext.getValue("count(//w[id='".concat(((W)foliaWrapperNext.get()).getId()).concat("']/preceding-sibling::*)"))
					- (Double)this.foliaContext.getValue("count(//w[id='".concat(((W)this.object).getId()).concat("']/preceding-sibling::*)")))
				) {
					return foliaWrapperNext;
				} else {
					return foliaWrapperPrev;
				}
			} else {
				return foliaWrapperPrev;
			}
		}
	}
	
	/** Returns previous element relative to object.
	 * @return		FoliaWrapper containing previous sibling as object.
	 */
	public FoliaWrapper prev() {
		try {
			return new FoliaWrapper(
				this.foliaContext.getValue("//w[id='".concat(((W)this.object).getId()).concat("']/preceding-sibling::w[1]")),
				this.foliaContext
			);
		} catch(NullPointerException e) {
			//Console.log("prev", e.toString());
			return new FoliaWrapper();
		}
	}
	
	/** Returns previous element relative to object that matches selector.
	 * @param selector	Query to which previous object will be matched.
	 * @return			FoliaWrapper containing previous sibling as object.
	 */
	public FoliaWrapper prev(String selector) {
		return this.prev(selector, null);
	}
	
	/** Returns previous element relative to object that matches selector. If a previous node matches excludor, iteration is stopped and empty FoliaWrapper is returned.
	 * @param selector	Query to which previous object will be matched.
	 * @param excludor	Query through which previous object will be excluded.
	 * @return			FoliaWrapper containing previous sibling as object.
	 */
	public FoliaWrapper prev(String selector, String excluder) {
		FoliaWrapper o = this.prev();
		
		while (o.val(selector).get() == null) {
			o = o.prev();
			
			if (o.get() == null)
				break;
			else if (o.val(excluder).get() != null) {
				o.set(null);
				break;
			}
		}
		
		return o;
	}
	
	/** Returns next element relative to object.
	 * @return			FoliaWrapper containing next sibling as object.
	 */
	public FoliaWrapper next() {
		try {
			return new FoliaWrapper(
				this.foliaContext.getValue("//w[id='".concat(((W)this.object).getId()).concat("']/following-sibling::w[1]")),
				this.foliaContext
			);
		} catch(NullPointerException e) {
			//Console.log("next", e.toString());
			return new FoliaWrapper();
		}
	}
	
	/** Returns next element relative to object that matches selector.
	 * @param selector	Query to which next object will be matched.
	 * @return			FoliaWrapper containing next sibling as object.
	 */
	public FoliaWrapper next(String selector) {
		return this.next(selector, null);
	}
	
	/** Returns next element relative to object that matches selector. If a next node matches excludor, iteration is stopped and empty FoliaWrapper is returned.
	 * @param selector	Query to which next object will be matched.
	 * @param excludor	Query through which next object will be excluded.
	 * @return			FoliaWrapper containing next sibling as object.
	 */
	public FoliaWrapper next(String selector, String excluder) {
		FoliaWrapper o = this.next();
		
		while (o.val(selector).get() == null) {
			o = o.next();
			
			if (o.get() == null)
				break;
			else if (o.val(excluder).get() != null) {
				o.set(null);
				break;
			}
		}
		
		return o;
	}
	
	/** Returns root node of this.object.
	 * @return			FoliaWrapper containing root node.
	 */
	public FoliaWrapper root() {
		FoliaWrapper foliaWrapper = this;
		FoliaWrapper root = this;
		
		while (foliaWrapper.exists()) {
			foliaWrapper = foliaWrapper.parent();
			if (foliaWrapper.exists())
				root = foliaWrapper;
		}
		
		return root;
	}
	
	public FoliaWrapper parent() {
		return this.parent(null);
	}
	
	/** Returns first ancestor that matches selector.
	 * @param selector		Query to which ancestors will be matched.
	 * @return				FoliaWrapper containing ancestor relative to object.
	 */
	public FoliaWrapper parent(String selector) {
		FoliaWrapper root = this.folia();
		FoliaWrapper o = this;
		
		while(
			root.val("//dependency/dep/wref[@idAttribute='".concat(((W)o.get()).getId()).concat("']")).exists()
		) {
			FoliaWrapper parentNode = root.val("text/p/s/w[@id='".concat(
				(String)root.val("//dependency/dep/wref[@idAttribute='".concat(
					((String)((W)o.get()).getId())
				).concat("']/../../hd/wref/@idAttribute")).get()
			).concat("']"));
			
			if (selector == null && parentNode.exists())
				return parentNode;
			if (parentNode.val(selector).exists())
				return parentNode;
			else
				o = parentNode;
		}
		
		return new FoliaWrapper();
	}
	
	/** Private function returning all children of this.object, optionally recursive.
	 * @param recursive		Boolean indicating whether all children's children etc. should be included.
	 * @return				Flat (i.e. 1-level) ArrayList containing FoliaWrappers containing descendants.
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<FoliaWrapper> children(boolean recursive) {
		ArrayList<FoliaWrapper> matches = new ArrayList<FoliaWrapper>();
		Iterator<Dependency> iterator = this.foliaContext.iterate("//dependencies/dependency/hd/wref[@idAttribute='".concat(this.val("@id").getAsString()).concat("']/../.."));
		
		while (iterator.hasNext()) {
			Dependency dependency = iterator.next();
			
			FoliaWrapper match = new FoliaWrapper(
				this.foliaContext.getValue("//w[@id='".concat(dependency.dep.get(0).wref.get(0).idAttribute).concat("']")),
				this.foliaContext
			);
			
			matches.add(match);
			
			if (recursive)
				matches.addAll(match.children(true));
		}
		
		return matches;
	}
	
}
