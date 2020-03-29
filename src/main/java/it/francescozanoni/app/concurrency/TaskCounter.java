package it.francescozanoni.app.concurrency;

public class TaskCounter {

    private int started = 0;
    private int finished = 0;
    private int failed = 0;

    public synchronized void incrementStarted() {
        this.started++;
    }

    public synchronized void incrementFinished() {
        this.finished++;
    }

    public synchronized void incrementFailed() {
        this.failed++;
    }

    public synchronized int getStarted() {
        return this.started;
    }

    public synchronized int getFinished() {
        return this.finished;
    }

    public synchronized int getFailed() {
        return this.failed;
    }

}
