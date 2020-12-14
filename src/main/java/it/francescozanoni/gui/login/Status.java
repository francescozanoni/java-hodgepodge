package it.francescozanoni.gui.login;

// Inspired by https://www.geeksforgeeks.org/singleton-class-java
public final class Status {

    private static Status instance = null;

    public Page page;
    public String username;

    private Status() {}

    public static Status getInstance() {
        if (instance == null) {
            instance = new Status();
        }
        return instance;
    }
}
