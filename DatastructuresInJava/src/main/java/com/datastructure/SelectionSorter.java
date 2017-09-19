package com.datastructure;

import java.util.Arrays;

/*Steps to selection sort
 * 1. first smallest item of array and replace with first item.
 * 2. Move the minIndex to next element.
 * 3. again, first the smallest item of array and replace with element that presnet in minIndex.
 * */
public class SelectionSorter {
	
	public static void main(String[] args) {
		int[] data = {7,3,6,8,2};
		new SelectionSorter().sort(data);
		System.out.println(Arrays.toString(data));
	}
	
	public void sort(int[] data) {
		for (int i = 0; i < data.length-1; i++) {
			int minIndex = i;
			for (int j = i+1; j < data.length; j++) {
				if (data[j] < data[minIndex]) {
					minIndex = j;
				}
			}
			int tmp = data[minIndex];
			data[minIndex] = data[i];
			data[i] = tmp;
		}
	}

}
