package com.sayan.dsa;

import java.util.Random;

/**
 * This class is a comparison of linear search and binary search algorithms
 * across different input sizes and number of searches.
 * @author Sayan Biswas
 * @version 09.07.2024
 */
public class SearchAlgorithmComparison {
    static int linearSearchComparisons = 0;
    static int binarySearchComparisons = 0;
    static int sortComparisons = 0;

    /**
     * Starting point of program
     * Calls other methods
     * @param args Arguments passed to the main method
     */
    public static void main(String[] args) {
        int[] n = {1000, 5000, 10000}; // Various input sizes
        int[] m = {1, 100, 500, 1000, 5000, 8000, 10000}; // Various number of searches

        for (int size : n) {
            System.out.println("For input size = " + size);
            System.out.println("---Linear Search---");
            for (int searches : m) {
                linearSearchComparisons = 0; // Reset comparisons for each iteration
                // Print in the format (number of searches, number of comparisons)
                System.out.print("(" + searches + ", ");
                linearSearchWorker(size, searches);
            }
            System.out.println();
            System.out.println("---Binary Search---");
            for (int searches : m) {
                binarySearchComparisons = 0; // Reset comparisons for each iteration
                // Print in the format (number of searches, number of comparisons)
                System.out.print("(" + searches + ", ");
                binarySearchWorker(size, searches);
            }
            System.out.println("\n");
        }
    }

    /**
     * Applies Linear Search on an array of a given size a given number of times
     * @param n Size of array
     * @param m Number of searches
     */
    private static void linearSearchWorker(int n, int m) {
        SearchAlgorithmComparison ob = new SearchAlgorithmComparison();
        Random rand = new Random();
        int[] arr = ob.arrayFiller(n);
        for (int i = 0; i < m; i++) {
            ob.linearSearch(arr, n, rand.nextInt(n));
        }
        System.out.print(linearSearchComparisons + "), ");
    }

    /**
     * Applies Binary Search on an array of a given size a given number of times
     * @param n Size of array
     * @param m Number of searches
     */
    private static void binarySearchWorker(int n, int m) {
        SearchAlgorithmComparison ob = new SearchAlgorithmComparison();
        Random rand = new Random();
        int[] arr = ob.arrayFiller(n);
        int[] sortedArr = ob.quickSort(arr); // Sort the array for binary search
        for (int i = 0; i < m; i++) {
            ob.binarySearch(sortedArr, n, rand.nextInt(n));
        }
        System.out.print(binarySearchComparisons + "), ");
    }

    /**
     * Fills up an array of a given size
     * @param size Size of array to be filled
     * @return Array filled with random elements
     */
    private int[] arrayFiller(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(size);
        }
        return arr;
    }

    /**
     * Applies Linear Search algorithm to search for an element in an array
     * Returns no index as it is not required for this use case
     * @param a Array to be searched
     * @param arraySize Size of array
     * @param searchItem Element to be searched
     */
    private void linearSearch(int[] a, int arraySize, int searchItem) {
        for (int i = 0; i < arraySize; i++) {
            linearSearchComparisons++; // Counter for comparisons
            if (a[i] == searchItem) {
                return;
            }
        }
    }

    /**
     * Applies Binary Search algorithm to search for an element in an array
     * Returns no index as it is not required for this use case
     * @param a Array to be searched
     * @param arraySize Size of array
     * @param searchItem Element to be searched
     */
    private void binarySearch(int[] a, int arraySize, int searchItem) {
        int left = 0;
        int right = arraySize - 1;

        while (left <= right) {
            binarySearchComparisons++; // Counter for comparisons
            int mid = left + (right - left) / 2;
            if (a[mid] == searchItem) {
                return;
            }
            if (a[mid] < searchItem) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    /**
     * Sorts an array using Quick Sort algorithm
     * @param arr Array to be sorted
     * @return Sorted Array
     */
    private int[] quickSort(int[] arr) {
        // Call the recursive quicksort function
        quickSort(arr, 0, arr.length - 1);
        binarySearchComparisons += sortComparisons;
        return arr;
    }

    /**
     * Recursive QuickSort Function
     * @param arr Array to be sorted
     * @param low Starting index of subarray
     * @param high Ending index of subarray
     */
    private void quickSort(int[] arr, int low, int high) {
        sortComparisons++;
        if (low < high) {
            // Partition the array, arr[p...q] is now in place
            int partitionIndex = partition(arr, low, high);
            // Recursively sort elements before partition and after partition
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    /**
     * Puts elements less than pivot on left and greater than pivot on right
     * @param arr Array to be sorted
     * @param low Starting index of subarray
     * @param high Ending index of subarray
     * @return Index of pivot element
     */
    private int partition(int[] arr, int low, int high) {
        // Choose the pivot element (in this case, the last element)
        int pivot = arr[high];
        int i = low - 1; // Index of smaller element
        for (int j = low; j < high; j++) {
            sortComparisons++;
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap arr[i + 1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}