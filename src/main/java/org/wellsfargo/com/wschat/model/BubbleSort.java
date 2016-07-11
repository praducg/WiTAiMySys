package org.wellsfargo.com.wschat.model;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] array = { 1, 5, 2, 7, 8, 3 };
		System.out.println("\nFinal result:"
				+ Arrays.toString(bubbleSort(array)));
		System.out.println(bubbleSort(array));
	}

	static int[] bubbleSort(int arr[]) {
		int temp;
		for (int i = 0; i < arr.length - 1; i++) {

			for (int j = 1; j < arr.length - i; j++) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
			System.out.println((i + 1) + "th iteration result: "
					+ Arrays.toString(arr));
		}
		return arr;
	}
}
