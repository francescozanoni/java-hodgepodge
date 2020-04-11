# Basic Java FX example

## Compile/run separate .class files

    FX=/path/to/javafx-sdk-N.N.N/lib

    cd /path/to/project

    javac -classpath $FX/javafx.fxml.jar:$FX/javafx.base.jar:$FX/javafx.controls.jar:$FX/javafx.graphics.jar \
          Main.java \
          Controller.java

    java --module-path $FX \
         --add-modules=javafx.controls,javafx.fxml \
         Main

## Build/run executable .jar file

    jar cvfe App.jar Main *.class

Sources:
 - https://stackoverflow.com/questions/52467561/intellij-cant-recognize-javafx-11-with-openjdk-11
 - http://www.skylit.com/javamethods/faqs/createjar.html