package com.powerledger.assignment.sortingrestapi.sortstrategy;

import com.powerledger.assignment.sortingrestapi.annotation.SortingStrategy;
import com.powerledger.assignment.sortingrestapi.model.ApiResponse;
import com.powerledger.assignment.sortingrestapi.model.ComplexityInfo;

/**
 * Created by @author Sankash on 5/10/2019
 */
@SortingStrategy("selection")
public class SelectionSort implements Sortable {
    /**
     * This method implements the Generic Selection Sort
     *
     * <p>
     * Has a worst case running time of:
     * O(n^2)
     * <p>
     * Has a average case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n^2)
     * <p>
     * Space complexity of:
     * O(1)
     * <p>
     *
     * @param arr The array to be sorted
     *            Sorts the array in increasing order
     **/
    @Override
    public <T extends Comparable<T>> T[] sort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // Initial index of min
            int min = i;

            for (int j = i + 1; j < n; j++) {
                if (SortUtils.less(arr[j], arr[min])) {
                    min = j;
                }
            }

            // Swapping if index of min is changed
            if (min != i) {
                SortUtils.swap(arr, i, min);
            }
        }

        return arr;
    }

    @Override
    public <T extends Comparable> ApiResponse performSort(T[] array){
        return  ApiResponse.builder()
                .array(sort(array))
                .complexityInfo(
                        ComplexityInfo.builder()
                                .averageComplexity("O(n^2)")
                                .bestComplexity("O(n^2)")
                                .worstComplexity("O(n^2)")
                                .spaceComplexity("O(1)")
                                .build()
                )
                .build();
    }
}

