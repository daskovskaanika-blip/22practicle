import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static void mergeSort(int[] arr, int left, int right) {

        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    static void merge(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }

        for (int i = 0; i < n2; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {

            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    static void countingSort(int[] arr) {

        int max = arr[0];
        int min = arr[0];

        for (int num : arr) {

            if (num > max) {
                max = num;
            }

            if (num < min) {
                min = num;
            }
        }

        int range = max - min + 1;
        int[] count = new int[range];

        for (int num : arr) {
            count[num - min]++;
        }

        int index = 0;

        for (int i = 0; i < range; i++) {

            while (count[i] > 0) {
                arr[index] = i + min;
                index++;
                count[i]--;
            }
        }
    }

    static void quickSort(int[] arr, int low, int high) {

        if (low < high) {

            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j] <= pivot) {

                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("введіть розмір масиву: ");
        int size = scanner.nextInt();

        System.out.print("від якого числа генерувати: ");
        int from = scanner.nextInt();

        System.out.print("до якого числа генерувати: ");
        int to = scanner.nextInt();

        int[] original = new int[size];

        for (int i = 0; i < size; i++) {
            original[i] = random.nextInt(to - from + 1) + from;
        }

        System.out.println("\nпочатковий масив:");
        System.out.println(Arrays.toString(original));

        int[] mergeArray = Arrays.copyOf(original, original.length);
        int[] countingArray = Arrays.copyOf(original, original.length);
        int[] quickArray = Arrays.copyOf(original, original.length);

        long start;
        long finish;

        start = System.nanoTime();

        mergeSort(mergeArray, 0, mergeArray.length - 1);

        finish = System.nanoTime();
        long mergeTime = finish - start;

        start = System.nanoTime();

        countingSort(countingArray);

        finish = System.nanoTime();
        long countingTime = finish - start;

        start = System.nanoTime();

        quickSort(quickArray, 0, quickArray.length - 1);

        finish = System.nanoTime();
        long quickTime = finish - start;

        System.out.println("\n===== Merge Sort =====");
        System.out.println(Arrays.toString(mergeArray));
        System.out.println("час виконання: " + mergeTime + " нс");

        System.out.println("\n===== Counting Sort =====");
        System.out.println(Arrays.toString(countingArray));
        System.out.println("xас виконання: " + countingTime + " нс");

        System.out.println("\n===== Quick Sort =====");
        System.out.println(Arrays.toString(quickArray));
        System.out.println("час виконання: " + quickTime + " нс");

        scanner.close();
    }
}