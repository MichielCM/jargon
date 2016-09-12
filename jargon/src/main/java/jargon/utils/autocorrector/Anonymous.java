package jargon.utils.autocorrector;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public final class Anonymous {

	public static Object run(Object object, String method, Object[] values) {
		try {
			ArrayList<Class<?>> klasses = new ArrayList<Class<?>>();
			for (Object value : values) {
				//if (value.getClass().isPrimitive()) {
					klasses.add((Class<?>)value.getClass().getField("TYPE").get(null));
				//} else {
				//	klasses.add(value.getClass());
				//}
			}
			
			object.getClass().getMethod(method, klasses.toArray(new Class<?>[klasses.size()])).invoke(object, values);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
	}
	
	public static Object run(Object object, String field, Object value) {
		try {
			object.getClass().getField(field).set(object, value);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
	}
	
}
