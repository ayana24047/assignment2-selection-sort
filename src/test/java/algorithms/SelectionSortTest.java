package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {

    @Test
    void empty() {
        int[] a = {};
        SelectionSort.sort(a, new PerformanceTracker(), true, true);
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    void reversed() {
        int[] a = {5, 4, 3, 2, 1};
        SelectionSort.sort(a, new PerformanceTracker(), true, true);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, a);
    }

    @Test
    void nearlySorted() {
        int[] a = {1, 2, 3, 5, 4, 6, 7};
        SelectionSort.sort(a, new PerformanceTracker(), true, true);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7}, a);
    }

    @Test
    void randomArray() {
        int[] a = {8, 2, 9, 1, 5, 3};
        SelectionSort.sort(a, new PerformanceTracker(), true, true);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, a);
    }
}
