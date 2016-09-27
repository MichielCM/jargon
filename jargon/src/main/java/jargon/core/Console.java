package jargon.core;

import java.util.Arrays;

public final class Console {
	public static void log(Object... objects) {
		System.out.println(
			Arrays.toString(objects)
		);
	}
}
