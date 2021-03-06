package com.powerledger.assignment.sortingrestapi.sortstrategy;

import com.powerledger.assignment.sortingrestapi.annotation.SortingStrategy;
import com.powerledger.assignment.sortingrestapi.model.ApiResponse;
import com.powerledger.assignment.sortingrestapi.model.ComplexityInfo;

/**
 * Created by @author Sankash on 5/10/2019
 */
@SortingStrategy("merge")
public class MergeSort implements Sortable {

    /**
     * @param arr   The array to be sorted
     * @param temp  The copy of the actual array
     * @param left  The first index of the array
     * @param right The last index of the array
     *              Recursively sorts the array in increasing order
     **/
    private static <T extends Comparable<T>> void doSort(T[] arr, T[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            doSort(arr, temp, left, mid);
            doSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }

    }

    /**
     * This method implements the merge step of the merge sort
     *
     * @param arr   The array to be sorted
     * @param temp  The copy of the actual array
     * @param left  The first index of the array
     * @param mid   The middle index of the array
     * @param right The last index of the array
     *              merges two parts of an array in increasing order
     **/

    private static <T extends Comparable<T>> void merge(T[] arr, T[] temp, int left, int mid, int right) {
        System.arraycopy(arr, left, temp, left, right - left + 1);


        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = temp[i++];
        }

        while (j <= right) {
            arr[k++] = temp[j++];
        }
    }

    /**
     * This method implements the Generic Merge Sort
     *
     * <p>
     * Has a worst case running time of:
     * O(n log n)
     * <p>
     * Has a average case running time of:
     * O(n log n)
     * <p>
     * And a best case running time of:
     * O(n log n)
     * <p>
     * Space complexity of:
     * O(n)
     * <p>
     *
     * @param unsorted the array which should be sorted
     * @param <T>      Comparable class
     * @return sorted array
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        T[] tmp = (T[]) new Comparable[unsorted.length];
        doSort(unsorted, tmp, 0, unsorted.length - 1);
        return unsorted;
    }

    @Override
    public <T extends Comparable> ApiResponse performSort(T[] array){
        return  ApiResponse.builder()
                .array(sort(array))
                .complexityInfo(
                        ComplexityInfo.builder()
                                .averageComplexity("O(n log n)")
                                .bestComplexity("O(n log n)")
                                .worstComplexity("O(n log n)")
                                .spaceComplexity("O(n)")
                                .build()
                )
                .build();
    }
}
