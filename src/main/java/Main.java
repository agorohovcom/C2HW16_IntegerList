import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }

        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private static void merge(Integer[] arr, Integer[] left, Integer[] right) {
        int mainP = 0;
        int leftP = 0;
        int rightP = 0;

        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }

        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }
}
