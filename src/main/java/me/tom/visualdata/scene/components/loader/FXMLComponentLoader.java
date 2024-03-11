package me.tom.visualdata.scene.components.loader;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class FXMLComponentLoader implements ComponentLoader {
    @Override
    public void load(String name, Class<?> loadedBy) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/fxml/components/NavigationBar.fxml"));
        loader.setRoot(loadedBy);
        loader.setController(loadedBy);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
