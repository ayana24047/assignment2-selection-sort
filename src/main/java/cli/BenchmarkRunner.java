package cli;

import algorithms.SelectionSort;
import metrics.PerformanceTracker;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        boolean earlyExit = true;
        boolean minMaxPass = true;

        int[] sizes = {100, 1000, 10_000, 100_000};

        System.out.println("dist,n,comparisons,swaps,reads,writes,ns");

        for (int n : sizes) {
            bench("random",   randomArray(n), earlyExit, minMaxPass);
            bench("sorted",   sortedArray(n), earlyExit, minMaxPass);
            bench("reversed", reversedArray(n), earlyExit, minMaxPass);
            bench("nearly",   nearlySorted(n, 0.02), earlyExit, minMaxPass);
        }
    }

    private static void bench(String dist, int[] a, boolean earlyExit, boolean minMaxPass) {
        PerformanceTracker t = new PerformanceTracker();
        SelectionSort.sort(a, t, earlyExit, minMaxPass);
        System.out.printf("%s,%d,%d,%d,%d,%d,%d%n",
                dist, a.length, t.comparisons, t.swaps, t.arrayReads, t.arrayWrites, t.elapsedNs());
    }

    private static int[] randomArray(int n) {
        Random r = new Random(0);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt(n);
        return a;
    }

    private static int[] sortedArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        return a;
    }

    private static int[] reversedArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = n - 1 - i;
        return a;
    }

    private static int[] nearlySorted(int n, double swapRate) {
        int[] a = sortedArray(n);
        Random r = new Random(1);
        int swaps = Math.max(1, (int)(n * swapRate));
        for (int k = 0; k < swaps; k++) {
            int i = r.nextInt(n), j = r.nextInt(n);
            int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
        }
        return a;
    }
}
