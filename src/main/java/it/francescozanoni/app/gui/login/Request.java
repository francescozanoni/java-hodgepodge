package it.francescozanoni.app.gui.login;

public class Request {

    public String url;
    public String result;

    public Request(String url, String result) {
        this.url = url;
        this.result = result;
    }

    /**
     * Property getters are required by table data binding logic
     *
     * @return String
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Property getters are required by table data binding logic
     *
     * @return String
     */
    public String getResult() {
        return result;
    }

}
