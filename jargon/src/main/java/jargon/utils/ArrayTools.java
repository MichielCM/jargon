package jargon.utils;

import java.util.Collections;
import java.util.List;

public final class ArrayTools {

	public static List<?> clean(final List<?> list) {
	    list.removeAll(Collections.singleton(null));
	    return list;
	}
	
}
