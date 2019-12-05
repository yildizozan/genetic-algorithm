package com.yildizozan;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Misc {
	public static int find(double[] arr, double item) {
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (item < arr[i]) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static int[] suffle(int numberOfElements) {
		Integer[] arr = new Integer[numberOfElements];
		for (int i = 0; i < numberOfElements; i++) {
			arr[i] = i;
		}
		List<Integer> list = Arrays.asList(arr);
		Collections.shuffle(list);
//		list.forEach(x -> System.out.println(x));


		// Convert primitive array
		int[] ret = new int[numberOfElements];
		for (int i = 0; i < numberOfElements; i++) {
			ret[i] = list.get(i);
		}

		return ret;
	}
}
