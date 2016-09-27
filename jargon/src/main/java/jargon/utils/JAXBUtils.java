package jargon.utils;

import java.util.stream.Collectors;

public final class JAXBUtils {

	public static java.util.List<?> getElementsByType(java.util.List<?> input, Class<?> type) {
		return input
			.stream()
			.filter(
				obj -> obj.getClass().equals(type)
			).collect(
				Collectors.toList()
			);
	}
	
}
