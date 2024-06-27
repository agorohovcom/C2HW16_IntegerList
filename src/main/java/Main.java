import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Integer[] array = createRandomIntegerArray(100000, 100);
        System.out.println("Создан массив для сортировки:");
//        System.out.println(Arrays.toString(array));
        System.out.println("=============================");
        bubbleSort(array);
        selectionSort(array);
        insertionSort(array);
        defaultSort(array);
    }

    private static Integer[] insertionSort(Integer[] arrToSort) {
        long start = System.currentTimeMillis();
        Integer[] array = Arrays.copyOf(arrToSort, arrToSort.length);

        for (int i = 1; i < array.length; i++) {
            Integer tmp = array[i];
            int j = i;
            while(j > 0 && array[j-1] >= tmp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = tmp;
        }

        System.out.println("InsertionSort  time: " + (System.currentTimeMillis() - start) + " ms");
//        System.out.println(Arrays.toString(array));
        System.out.println("-----------------------------");
        return array;
    }

    private static Integer[] bubbleSort(Integer[] arrToSort) {
        long start = System.currentTimeMillis();
        Integer[] array = Arrays.copyOf(arrToSort, arrToSort.length);

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }

        System.out.println("BubbleSort  time: " + (System.currentTimeMillis() - start) + " ms");
//        System.out.println(Arrays.toString(array));
        System.out.println("-----------------------------");
        return array;
    }

    private static Integer[] selectionSort(Integer[] arrToSort) {
        long start = System.currentTimeMillis();
        Integer[] array = Arrays.copyOf(arrToSort, arrToSort.length);

        for (int i = 0; i < array.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            int tmp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = tmp;
        }


        System.out.println("SelectionSort  time: " + (System.currentTimeMillis() - start) + " ms");
//        System.out.println(Arrays.toString(array));
        System.out.println("-----------------------------");
        return array;
    }

    private static Integer[] defaultSort(Integer[] arrToSort) {
        long start = System.currentTimeMillis();
        Integer[] array = Arrays.copyOf(arrToSort, arrToSort.length);

        Arrays.sort(array);

        System.out.println("Arrays.sort()  time: " + (System.currentTimeMillis() - start) + " ms");
//        System.out.println(Arrays.toString(array));
        System.out.println("-----------------------------");
        return array;
    }

    private static Integer[] createRandomIntegerArray(int size, int maxItemValue) {
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++) {
            result[i] = new Random().nextInt(maxItemValue);
        }
        return result;
    }
}
