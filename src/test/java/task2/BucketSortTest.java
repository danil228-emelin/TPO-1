package task2;

import org.example.task2.BucketSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BucketSortTest {

    @Test
    @DisplayName("Test with an empty array")
    void testEmptyArray() {
        double[] arr = {};
        BucketSort.bucketSort(arr, 5);
        assertArrayEquals(new double[]{}, arr, "An empty array should remain empty");
    }

    @Test
    @DisplayName("Test with a single element")
    void testSingleElementArray() {
        double[] arr = {0.5};
        BucketSort.bucketSort(arr, 5);
        assertArrayEquals(new double[]{0.5}, arr, "An array with a single element should remain unchanged");
    }

    @Test
    @DisplayName("Test with an already sorted array")
    void testAlreadySortedArray() {
        double[] arr = {0.1, 0.2, 0.3, 0.4, 0.5};
        BucketSort.bucketSort(arr, 5);
        assertArrayEquals(new double[]{0.1, 0.2, 0.3, 0.4, 0.5}, arr, "An already sorted array should remain unchanged");
    }

    @Test
    @DisplayName("Test with a reverse-sorted array")
    void testReverseSortedArray() {
        double[] arr = {0.5, 0.4, 0.3, 0.2, 0.1};
        BucketSort.bucketSort(arr, 5);
        assertArrayEquals(new double[]{0.1, 0.2, 0.3, 0.4, 0.5}, arr, "A reverse-sorted array should be sorted");
    }

    @Test
    @DisplayName("Test with an array containing duplicates")
    void testArrayWithDuplicates() {
        double[] arr = {0.3, 0.1, 0.2, 0.3, 0.1};
        BucketSort.bucketSort(arr, 5);
        assertArrayEquals(new double[]{0.1, 0.1, 0.2, 0.3, 0.3}, arr, "An array with duplicates should be sorted");
    }

    @Test
    @DisplayName("Test with an array containing negative numbers")
    void testArrayWithNegativeNumbers() {
        double[] arr = {-0.5, -0.1, -0.3, 0.0, 0.2};
        BucketSort.bucketSort(arr, 5);
        assertArrayEquals(new double[]{-0.5, -0.3, -0.1, 0.0, 0.2}, arr, "An array with negative numbers should be sorted");
    }

    @Test
    @DisplayName("Test with a large array")
    void testLargeArray() {
        double[] arr = {0.9, 0.1, 0.6, 0.7, 0.3, 0.4, 0.2, 0.8, 0.5, 0.0};
        BucketSort.bucketSort(arr, 5);
        assertArrayEquals(new double[]{0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9}, arr, "A large array should be sorted");
    }

    @Test
    @DisplayName("Test with an array containing all identical elements")
    void testArrayWithAllSameElements() {
        double[] arr = {0.5, 0.5, 0.5, 0.5};
        BucketSort.bucketSort(arr, 5);
        assertArrayEquals(new double[]{0.5, 0.5, 0.5, 0.5}, arr, "An array with all identical elements should remain unchanged");
    }
}