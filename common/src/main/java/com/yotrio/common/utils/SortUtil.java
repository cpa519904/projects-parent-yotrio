package com.yotrio.common.utils;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class SortUtil {
	/**
	 * Sort an array with on a property, the sort will be done in a ascend order
	 * @param array
	 */
	public static final void sortArray(Object[] array, boolean isDescOrder) {
		sortArray(array, null, isDescOrder);
	}
	/**
	 * Sort an array with on a property, the sort will be done in a ascend order
	 * @param array An array of objects
	 * @param propName A property in the object of the list
	 */
	public static final void sortArray(Object[] array, String propName, boolean isDescOrder) {
		// The sort operation must be done after a shuffle
		List objectList = Arrays.asList(array);
		sortList(objectList, propName, isDescOrder);
	}
	/**
	 * Sort list values,the sort will be done in a ascend order
	 * @param aList A list of simple object
	 */
	public static final void sortList(List aList, boolean isDescOrder) {
		sortList(aList, null, isDescOrder);
	}
	/**
	 * Sort list objects with on a property, the sort will be done in a ascend order
	 * @param aList A list of compound object
	 * @param propName A property in the object of the list
	 * @param isDescOrder Is sort in descent order
	 */
	public static final void sortList(List aList, String propName, boolean isDescOrder) {
		// The sort operation must be done after a shuffle
		Collections.shuffle(aList);
		ListComparator comp = new ListComparator(propName, isDescOrder);
		Collections.sort(aList, comp);
	}
	/**
	 * Compare two value, for now, we could only compare Integer, String and Boolean.The compare result as belon: Integer: val1>val2, return 1,or -1 on the opposite String : val1>val2, return 1,or -1 on the opposite Boolean: val1 == true, return 1,or -1 when it is false This will return 0 if two value are the same or they are not comparable
	 * @param val1 Value1
	 * @param val2 Value2
	 * @return
	 */
	public static final int compareValue(Object val1, Object val2) {
		int result = 0;
		if (val1 == null)
			result = -1;
		else if (val2 == null)
			result = 1;
		else if (val1 instanceof Integer)
			result = ((Integer) val1 - (Integer) val2) * -1;
		else if (val1 instanceof String) {
			String strVal1 = (String) val1;
			String strVal2 = (String) val2;
			result = strVal2.compareTo(strVal1);
		} else if (val1 instanceof Boolean) {
			// for boolean type, true will be sorted before,
			// or at the bottom when desc is set to false
			if ((Boolean) val1 == true) {
				result = 1;
			}
		} else {
			// for other data type, cannot compare
			result = 0;
		}
		return result;
	}
	/**
	 * Default list comparator for list object
	 * @author Watson
	 */
	public static class ListComparator implements Comparator {
		// indicates which property should be compare for a compound object
		private String propName;
		// indicates whether the result should be sort in descend order
		private boolean desc;
		public ListComparator(boolean isDesc) {
			this.desc = isDesc;
		}
		public ListComparator(String propName, boolean isDesc) {
			this.desc = isDesc;
			this.propName = propName;
		}
		public int compare(Object o1, Object o2) {
			int result = 0;
			int order = 1;
			// compare with class type first
			if (o1.getClass().equals(o2.getClass())) {
				if (!desc)
					order = -1;
				// if property name not null, found the property's value
				if (StringUtil.isNotEmpty(propName)) {
					Object val1 = BeanUtil.getProperty(o1, propName);
					Object val2 = BeanUtil.getProperty(o2, propName);
					result = compareValue(val1, val2) * order;
				} else {
					result = compareValue(o1, o2) * order;
				}
			} else {
				// skip this compare
				result = 0;
			}
			return result;
		}
	}
}
