package org.example.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public static void bucketSort(double[] arr, int bucketSize) {
        if (arr.length == 0) {
            return;
        }

        // Находим минимальное и максимальное значения в массиве
        double minValue = arr[0];
        double maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        // Инициализируем бакеты
        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketSize) + 1;
        List<List<Double>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Распределяем элементы по бакетм
        for (double value : arr) {
            int bucketIndex = (int) Math.floor((value - minValue) / bucketSize);
            buckets.get(bucketIndex).add(value);
        }

        // Сортируем каждый бакет и собираем элементы обратно в массив
        int currentIndex = 0;
        for (List<Double> bucket : buckets) {
            Collections.sort(bucket); // Используем встроенную сортировку
            for (double value : bucket) {
                arr[currentIndex++] = value;
            }
        }
    }
}
