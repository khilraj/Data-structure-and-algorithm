package src;

import java.util.Arrays;

public class Question3A {

    public static void main(String[] args) {
            int[] arr = {5, 2, 4, 11};
            int minDiff = minProductDifference(arr);
            System.out.println(minDiff);  // prints 2
        }

    public static int minProductDifference(int[] arr) {
            // Sort the array in non-decreasing order
            Arrays.sort(arr);

            // Divide the array into two subarrays
            int[] firstSubarray = Arrays.copyOfRange(arr, 0, arr.length/2);
            int[] secondSubarray = Arrays.copyOfRange(arr, arr.length/2, arr.length);

            // Find the minimum and maximum product of elements in the subarrays
            int minProduct = Integer.MAX_VALUE;
            int maxProduct = Integer.MIN_VALUE;
            for (int i = 0; i < firstSubarray.length; i++) {
                for (int j = 0; j < secondSubarray.length; j++) {
                    int product = firstSubarray[i] * secondSubarray[j];
                    if (product < minProduct) {
                        minProduct = product;
                    }
                    if (product > maxProduct) {
                        maxProduct = product;
                    }
                }
            }

            // Return the absolute difference between the minimum and maximum products
            return Math.abs(maxProduct - minProduct);
        }

}
