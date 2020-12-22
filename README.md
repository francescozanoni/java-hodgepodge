# java-hodgepodge

Several basic examples and experiments with Java (>=11).

Thorough documentation sources are listed within code comments.

---

### Experiments

* `no_build_tool`: basic [JavaFX](https://openjfx.io) example, without [Maven](https://maven.apache.org)
* `src/main/java/it/francescozanoni`:
  * `async`: asynchronicity, concurrency and lambda expressions
  * `concurrency/db`: concurrency while writing to database
  * `concurrency/http`: web pages concurrent download, with URLs [loaded from JSON](https://howtodoinjava.com/java/library/json-simple-read-write-json-examples) file and IBAN code extraction via [regular expression](https://www.vogella.com/tutorials/JavaRegularExpressions/article.html)
  * `concurrency/prodcons`: shared buffer populated by a Producer thread and polled by a Consumer Thread
  * `gui`: several [JavaFX](https://openjfx.io) examples
  * `files`: examples of file management
  * `App.java`: dumb application used for dumb test and as package executable entry point
  * `web`: basic web application based on [HttpServer](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpServer.html), framework-less
* `src/test/java/it/francescozanoni/AppTest.java`: dumb test of dumb application

---

### Maven commands

* compilation:
    ```
    mvn compile
    ```
* package generation:
    ```
    mvn package
    ```
* single class execution:
  * without external dependencies:
    ```
    java -cp target/classes it.francescozanoni.web.Main
    # or
    java -cp target/original-java-hodgepodge-1.0-SNAPSHOT.jar it.francescozanoni.web.Main
    ```
  * with external dependencies:
    ```
    java -cp target/java-hodgepodge-1.0-SNAPSHOT.jar it.francescozanoni.concurrency.http.Main
    ```
    This is achieved via [maven-shade-plugin](https://maven.apache.org/plugins/maven-shade-plugin), which embeds all dependencies inside the package.
    If not run this way, a `java.lang.ClassNotFoundException` is raised.
  * JavaFX class:
    ```
    mvn javafx:run -Djavafx.mainClass=it.francescozanoni.gui.example.FXMLExample
    ```
* package execution:
    ```
    java -jar target/java-hodgepodge-1.0-SNAPSHOT.jar
    ```

---

### Tips for IntelliJ IDEA

* https://stackoverflow.com/questions/20496239/maven-plugins-can-not-be-found-in-intellij
