package metrics;

public class PerformanceTracker {
    public long comparisons;
    public long swaps;
    public long arrayReads;
    public long arrayWrites;
    private long startNs, endNs;

    public void start() { startNs = System.nanoTime(); }
    public void stop()  { endNs = System.nanoTime(); }
    public long elapsedNs() { return endNs - startNs; }

    public void reset() {
        comparisons = swaps = arrayReads = arrayWrites = 0;
        startNs = endNs = 0;
    }
}
