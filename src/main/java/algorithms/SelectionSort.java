package algorithms;

import metrics.PerformanceTracker;

public class SelectionSort {

    public static void sort(int[] a, PerformanceTracker t, boolean earlyExit, boolean minMaxPass) {
        if (a == null) return;
        if (t == null) t = new PerformanceTracker();
        t.start();

        int n = a.length;
        if (n < 2) { t.stop(); return; }

        if (earlyExit && isSorted(a, t)) { t.stop(); return; }

        int left = 0, right = n - 1;
        while (left < right) {
            int minIdx = left, maxIdx = left;
            boolean inversionSeen = false;
            int prev = read(a, left, t);

            for (int j = left; j <= right; j++) {
                int v = read(a, j, t);

                if (j > left) {
                    t.comparisons++;
                    if (v < prev) inversionSeen = true;
                }

                t.comparisons++;
                if (v < read(a, minIdx, t)) minIdx = j;
                t.comparisons++;
                if (v > read(a, maxIdx, t)) maxIdx = j;

                prev = v;
            }

            if (minIdx != left) swap(a, left, minIdx, t);
            if (maxIdx == left) maxIdx = minIdx;
            if (minMaxPass && maxIdx != right) swap(a, right, maxIdx, t);

            left++;
            right--;

            if (earlyExit && !inversionSeen) break;
        }

        t.stop();
    }

    private static boolean isSorted(int[] a, PerformanceTracker t) {
        for (int i = 1; i < a.length; i++) {
            t.comparisons++;
            if (read(a, i, t) < read(a, i - 1, t)) return false;
        }
        return true;
    }

    private static int read(int[] a, int i, PerformanceTracker t) {
        t.arrayReads++;
        return a[i];
    }

    private static void write(int[] a, int i, int v, PerformanceTracker t) {
        t.arrayWrites++;
        a[i] = v;
    }

    private static void swap(int[] a, int i, int j, PerformanceTracker t) {
        if (i == j) return;
        int tmp = read(a, i, t);
        write(a, i, read(a, j, t), t);
        write(a, j, tmp, t);
        t.swaps++;
    }
}
