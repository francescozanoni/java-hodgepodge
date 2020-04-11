# Basic Java FX example

FX=/path/to/javafx-sdk-A.B.C/lib

cd /path/to/project

javac -classpath $FX/javafx.fxml.jar:$FX/javafx.base.jar:$FX/javafx.controls.jar:$FX/javafx.graphics.jar \
      Main.java \
      Controller.java

java --module-path $FX \
     --add-modules=javafx.controls,javafx.fxml \
     -classpath . \
     Main

Sources:
 - https://stackoverflow.com/questions/52467561/intellij-cant-recognize-javafx-11-with-openjdk-11