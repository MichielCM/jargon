package jargon.model.folia;

import org.apache.commons.jxpath.JXPathContext;

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
	public FoliaWrapper root() {
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
	
	public boolean isNull() {
		return this.notExists();
	}
	
	public boolean notExists() {
		return this.object == null;
	}
	
	public boolean exists() {
		return !this.notExists();
	}
	
	/** Return true if selector run on object is equal to comparator.
	 * @param selector		Query relative to object.
	 * @param comparator	Object to which query result is compared.
	 * @return				True if comparator is equal to query's result.
	 */
	public boolean equals(String selector, Object comparator) {
		return this.objectContext.getValue(selector).equals(comparator);
	}
	
	/** Return true if selector run on object is equal to comparator regex.
	 * @param selector		Query relative to object.
	 * @param comparator	RegEx string to which query result is compared.
	 * @return				True if comparator is equal to query's result.
	 */
	public boolean matches(String selector, String comparator) {
		return ((String)this.objectContext.getValue(selector)).matches(comparator);
	}
	
	/** Returns node wrapped in FoliaWrapper class. Ideal for chaining.
	 * @param identifiedNode	Node to be wrapped.
	 * @return					FoliaWrapper object containing identifiedNode.
	 */
	public FoliaWrapper node(Object identifiedNode) {
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
			Console.log("val", selector, e.toString());
			return new FoliaWrapper();
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
			Console.log("prev", e.toString());
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
			Console.log("next", e.toString());
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
	
	/** Returns first ancestor that matches selector.
	 * @param selector		Query to which ancestors will be matched.
	 * @return				FoliaWrapper containing ancestor relative to object.
	 */
	public FoliaWrapper parent(String selector) {
		FoliaWrapper root = this.root();
		FoliaWrapper o = this;
		
		while(
			root.val("//dependency/dep/wref[@idAttribute='".concat(((W)o.get()).getId()).concat("']")).exists()
		) {
			FoliaWrapper parentNode = root.val("text/p/s/w[@id='".concat(
				(String)root.val("//dependency/dep/wref[@idAttribute='".concat(
					((String)((W)o.get()).getId())
				).concat("']/../../hd/wref/@idAttribute")).get()
			).concat("']"));
			
			if (parentNode.val(selector).exists())
				return parentNode;
			else
				o = parentNode;
		}
		
		return new FoliaWrapper();
	}
	
	
}
