# java-hodgepodge

Several basic examples and experiments with Java (>=11).

Documentation sources within code comments.

---

### Experiments
* `no_build_tool`: basic [JavaFX](https://openjfx.io) example, without [Maven](https://maven.apache.org)
* `src/main/java/it/francescozanoni/app`:
  * `async`: asynchronicity, concurrency and lambda expressions
  * `cli`: command line app with [picocli](https://picocli.info) package
  * `concurrency/db`: concurrency while writing to database
  * `gui`: several [JavaFX](https://openjfx.io) examples
  * `App.java`: dumb application used for dumb test
* `src/test/java/it/francescozanoni/app/AppTest.java`: dumb test of dumb application

---

### Package generation
```
mvn package

java -jar target/java-hodgepodge-1.0-SNAPSHOT.jar
```