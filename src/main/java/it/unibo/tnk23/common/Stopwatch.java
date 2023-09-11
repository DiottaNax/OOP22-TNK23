package it.unibo.tnk23.common;

public class Stopwatch {
    private boolean isRunning = false;
    private long last;

    public void start() {
        this.last = System.currentTimeMillis();
        this.isRunning = true;
    }
    
    public long getElapsedTime() {
        return System.currentTimeMillis() - this.last;
    }

    public void restart() {
        this.last = System.currentTimeMillis();
    }

    public void stop() {
        this.isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
