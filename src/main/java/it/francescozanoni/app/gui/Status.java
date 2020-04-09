package it.francescozanoni.app.gui;

// Inspired by https://www.geeksforgeeks.org/singleton-class-java
public final class Status {

    private static Status instance = null;

    public String page;

    private Status() {}

    public static Status getInstance() {
        if (instance == null) {
            instance = new Status();
        }
        return instance;
    }
}
