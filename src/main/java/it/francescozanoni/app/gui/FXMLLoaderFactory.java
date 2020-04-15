package it.francescozanoni.app.gui;

import javafx.fxml.FXMLLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

/**
 * This factory is required as a consequence of the refactoring
 * that now provides Status singleton to controllers via injection.
 */
public final class FXMLLoaderFactory {

    public static FXMLLoader get() {

        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(clazz -> {
            // https://www.logicbig.com/how-to/code-snippets/jcode-reflection-class-getconstructor.html
            try {
                Constructor<?> c = clazz.getConstructor(Status.class);
                return c.newInstance(Status.getInstance());
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                // @todo remove this null
                return null;
            }
        });

        return loader;
    }

    public static FXMLLoader get(URL fxmlFileUrl) {

        FXMLLoader loader = FXMLLoaderFactory.get();

        loader.setLocation(fxmlFileUrl);

        return loader;
    }
}
