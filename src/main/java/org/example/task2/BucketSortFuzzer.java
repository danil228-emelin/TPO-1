package org.example.task2;

import java.util.Random;

public class BucketSortFuzzer {

    private static final int MAX_ARRAY_SIZE = 1000; // Maximum size of the array
    private static final int MAX_VALUE = 1000;      // Maximum value for array elements
    private static final int MIN_VALUE = -1000;     // Minimum value for array elements
    private static final int FUZZ_TESTS = 1000;    // Number of fuzz tests to run

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < FUZZ_TESTS; i++) {
            // Generate a random array size
            int arraySize = random.nextInt(MAX_ARRAY_SIZE) + 1; // Ensure size is at least 1

            // Generate a random array of doubles
            double[] arr = new double[arraySize];
            for (int j = 0; j < arraySize; j++) {
                arr[j] = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * random.nextDouble();
            }

            // Choose a random bucket size
            int bucketSize = random.nextInt(10) + 1; // Bucket size between 1 and 10

            System.out.println("Test #" + (i + 1));
            System.out.println("Input Array: " + arrayToString(arr));
            System.out.println("Bucket Size: " + bucketSize);

            // Run the bucket sort algorithm
            BucketSort.bucketSort(arr, bucketSize);

            System.out.println("Sorted Array: " + arrayToString(arr));

            // Validate the sorted array
            if (!isSorted(arr)) {
                System.err.println("Error: Array is not sorted!");
                System.err.println("Failed Input: " + arrayToString(arr));
                System.exit(1); // Exit if a test fails
            }

            System.out.println("Test passed!\n");
        }

        System.out.println("All " + FUZZ_TESTS + " tests passed successfully!");
    }

    /**
     * Checks if an array is sorted in ascending order.
     *
     * @param arr The array to check.
     * @return True if the array is sorted, false otherwise.
     */
    private static boolean isSorted(double[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts an array to a string for printing.
     *
     * @param arr The array to convert.
     * @return A string representation of the array.
     */
    private static String arrayToString(double[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}