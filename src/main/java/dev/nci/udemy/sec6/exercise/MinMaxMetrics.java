package dev.nci.udemy.sec6.exercise;

public class MinMaxMetrics {
    // Add all necessary member variables
    private volatile long minValue;
    private volatile long maxValue;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        // Add code here
        this.minValue = 0;
        this.maxValue = 0;


        // or init with: this.maxValue = Long.MIN_VALUE;
        // and: this.minValue = Long.MAX_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {
        // Add code here
        if (newSample > this.maxValue) {
            this.updateMax(newSample);
        } else if (newSample < this.minValue) {
            this.updateMin(newSample);
        }
    }

    /**
     * This is the ref `Solution`:
     */
    public void addSampleRef(long newSample) {
        synchronized (this) {
            this.minValue = Math.min(newSample, this.minValue);
            this.maxValue = Math.max(newSample, this.maxValue);
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return minValue;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return maxValue;
    }

    private synchronized void updateMax(long newSample) {
        if (newSample > this.maxValue) {
            this.maxValue = newSample;
        }
    }

    private synchronized void updateMin(long newSample) {
        if (newSample < this.minValue) {
            this.minValue = newSample;
        }
    }
}
