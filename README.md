# java-hodgepodge

Several basic examples and experiments with Java (>=11).

Thorough documentation sources are listed within code comments.

---

### Experiments

* `no_build_tool`: basic [JavaFX](https://openjfx.io) example, without [Maven](https://maven.apache.org)
* `src/main/java/it/francescozanoni/app`:
  * `async`: asynchronicity, concurrency and lambda expressions
  * `cli`: command line app with [picocli](https://picocli.info) package
  * `concurrency/db`: concurrency while writing to database
  * `concurrency/http`: web pages concurrent download, with URLs [loaded from JSON](https://howtodoinjava.com/java/library/json-simple-read-write-json-examples) file and IBAN code extraction via [regular expression](https://www.vogella.com/tutorials/JavaRegularExpressions/article.html)
  * `gui`: several [JavaFX](https://openjfx.io) examples
  * `App.java`: dumb application used for dumb test
* `src/test/java/it/francescozanoni/app/AppTest.java`: dumb test of dumb application

---

### Maven commands

* compilation:
    ```
    mvn compile
    ```
* single class execution:
    ```
    java -classpath target\classes it.francescozanoni.app.concurrency.http.Main
    ```
* package generation and execution:
    ```
    mvn package
    
    java -jar target/java-hodgepodge-1.0-SNAPSHOT.jar
    ```