package com.adobe.aem.guides.wknd.core;

// Java implementation of simple method to find
// minimum difference between any pair

import java.util.Map;
import java.util.TreeMap;

public class DistinctMeasurement {
    public static void main(String[] args) {
        int arr[] = new int[] { 6, 5, 4, 3, 7};
        int minDifference = findMinDifference(arr, arr.length);
        Map<Integer, String> map = new TreeMap<>();

        for(int i = 0; i <= arr.length - 2; i++) {
            for(int j = 0; j <= arr.length -1; j++) {
                if(j != i && minDifference == arr[i] - arr[j]) {
                    map.put(arr[i] + arr[j], arr[j] + " " + arr[i]);
                }

                if(j != i && minDifference == arr[j] - arr[i]) {
                    map.put(arr[i] + arr[j], arr[i] + " " + arr[j]);
                }
            }
        }

        for (String value : map.values()) {
            System.out.println(value);
        }
    }

    static int findMinDifference(int[] arr, int n) {
        int diff = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs((arr[i] - arr[j])) < diff) {
                    diff = Math.abs((arr[i] - arr[j]));
                }
            }
        }

        return diff;
    }
}
