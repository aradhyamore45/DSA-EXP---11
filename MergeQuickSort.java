import java.util.Scanner;
import java.util.Arrays;

public class MergeQuickSort {

    // Merge Sort
    static void mergeSort(double[] prices, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(prices, left, mid);
            mergeSort(prices, mid + 1, right);
            merge(prices, left, mid, right);
        }
    }

    static void merge(double[] prices, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        double[] L = new double[n1];
        double[] R = new double[n2];

        for (int i = 0; i < n1; i++)
            L[i] = prices[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = prices[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j])
                prices[k++] = L[i++];
            else
                prices[k++] = R[j++];
        }

        while (i < n1)
            prices[k++] = L[i++];

        while (j < n2)
            prices[k++] = R[j++];
    }

    // Quick Sort
    static void quickSort(double[] prices, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(prices, low, high);
            quickSort(prices, low, pivotIndex - 1);
            quickSort(prices, pivotIndex + 1, high);
        }
    }

    static int partition(double[] prices, int low, int high) {
        double pivot = prices[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (prices[j] <= pivot) {
                i++;
                double temp = prices[i];
                prices[i] = prices[j];
                prices[j] = temp;
            }
        }

        double temp = prices[i + 1];
        prices[i + 1] = prices[high];
        prices[high] = temp;

        return i + 1;
    }

    static void display(double[] prices) {
        System.out.println("Sorted product prices: " + Arrays.toString(prices));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of products must be positive.");
            sc.close();
            return;
        }

        double[] prices = new double[n];

        System.out.println("Enter prices of " + n + " products:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextDouble();
        }

        int choice;

        do {
            System.out.println("\\n--- SORTING MENU ---");
            System.out.println("1. Merge Sort");
            System.out.println("2. Quick Sort");
            System.out.println("3. Display Original Prices");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    double[] mergePrices = prices.clone();
                    mergeSort(mergePrices, 0, n - 1);
                    System.out.println("Prices sorted using Merge Sort:");
                    display(mergePrices);
                    break;
                }

                case 2: {
                    double[] quickPrices = prices.clone();
                    quickSort(quickPrices, 0, n - 1);
                    System.out.println("Prices sorted using Quick Sort:");
                    display(quickPrices);
                    break;
                }

                case 3:
                    System.out.println("Original product prices: "
                            + Arrays.toString(prices));
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
