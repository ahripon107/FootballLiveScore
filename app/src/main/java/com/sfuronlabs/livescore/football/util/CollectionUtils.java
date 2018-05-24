package com.sfuronlabs.livescore.football.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ripon on 3/4/17.
 */

public class CollectionUtils {

    /**
     * Takes a wide variety of input and converts or merges them all to a list.
     * Supports primitive (short, int), arrays of primitive or object, List, Set of objects or a
     * mix of them.
     * If multiple collections/arrays are given they are flatted together up to one level.
     * Supports a single level of elements.
     *
     * @param elements varargs of elements
     * @return list of elements
     */
    @SuppressWarnings("unchecked")
    public static List toList(Object... elements) {
        List mergedElements = new ArrayList();

        for (Object element : elements) {
            if (element == null) {
                continue;
            }

            Class eClass = element.getClass();
            if (eClass.isArray()) {
                mergedElements.addAll(toListCheckingType(element));
            } else if (element instanceof Collection) {
                mergedElements.addAll((Collection) element);
            } else if (element instanceof Map) {
                mergedElements.addAll(((Map) element).entrySet());
            } else {
                mergedElements.add(element);
            }
        }

        return mergedElements;
    }

    private static List toListCheckingType(Object array) {
        if (array == null) {
            return null;
        }

        Class aClass = array.getClass();
        if (aClass == short[].class) {
            return toListFromShort((short[]) array);
        } else if (aClass == int[].class) {
            return toListFromInt((int[]) array);
        } else if (array instanceof Object[]) {
            return toList((Object[]) array);
        }

        throw new IllegalArgumentException("Unsupported Type: " + array.getClass());
    }

    public static List<Short> toListFromShort(short... shortArray) {
        List<Short> list = new ArrayList<>(shortArray.length);

        for (short value : shortArray) {
            list.add(value);
        }

        return list;
    }

    public static List<Integer> toListFromInt(int... intArray) {
        List<Integer> list = new ArrayList<>(intArray.length);

        for (int value : intArray) {
            list.add(value);
        }

        return list;
    }

    public static List<? extends Comparable> toSortedList(Collection<? extends Comparable> data) {
        if (isEmpty(data)) {
            return null;
        }

        List<? extends Comparable> result = new LinkedList<>(data);
        Collections.sort(result);

        return result;
    }

    public static int[] toIntArray(Collection<Integer> list) {
        int[] intArray = new int[list.size()];

        if (CollectionUtils.isEmpty(list)) {
            return intArray;
        }

        int count = 0;
        for (Integer i : list) {
            intArray[count++] = i;
        }

        return intArray;
    }


    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean containsAny(Collection values, Collection requiredValues) {
        if (values == null) {
            return false;
        }

        for (Object requiredValue : requiredValues) {
            if (values.contains(requiredValue)) {
                return true;
            }
        }

        return false;
    }
}
