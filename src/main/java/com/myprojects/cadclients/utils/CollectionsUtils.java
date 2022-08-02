package com.myprojects.cadclients.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CollectionsUtils {

	public static <T> Set<T> convertListToSet(List<T> list) {
		return new HashSet<>(list);
	}

	public static <T> Set<T> convertListStreamToSet(List<T> list) {
		return list.stream().collect(Collectors.toSet());
	}

	public static <T> List<T> convertSetToList(Set<T> set) {
		List<T> list = new ArrayList<>(set);
		return list;
	}

	public static <T> List<T> convertSetStreamToList(Set<T> set) {
		return set.stream().collect(Collectors.toList());
	}

}
