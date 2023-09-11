package it.unibo.tnk23.common;

/**
 * A simple stopwatch utility class for measuring elapsed time.
 */
public class Stopwatch {
    private boolean isRunning = false;
    private long last;

    /**
     * Starts the stopwatch.
     * Records the current system time as the starting point.
     */
    public void start() {
        this.last = System.currentTimeMillis();
        this.isRunning = true;
    }

    /**
     * Retrieves the elapsed time since the stopwatch was started or restarted.
     *
     * @return The elapsed time in milliseconds.
     */
    public long getElapsedTime() {
        return System.currentTimeMillis() - this.last;
    }

    /**
     * Restarts the stopwatch by updating the starting point to the current system time.
     */
    public void restart() {
        this.last = System.currentTimeMillis();
    }

    /**
     * Stops the stopwatch, indicating that it is no longer running.
     */
    public void stop() {
        this.isRunning = false;
    }

    /**
     * Checks if the stopwatch is currently running.
     *
     * @return True if the stopwatch is running; false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }
}
