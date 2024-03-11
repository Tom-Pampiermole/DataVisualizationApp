package me.tom.visualdata.scene.components.loader;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class FXMLComponentLoader implements ComponentLoader {
    @Override
    public void load(String name, Object loadedBy) throws ComponentLoaderException {
        assert name != null && !name.trim().isEmpty() : "Cannot load component: name empty";
        assert loadedBy != null : "Cannot load component: loadedBy null";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(String.format("/scene/fxml/components/%s.fxml", name)));
        loader.setRoot(loadedBy);
        loader.setController(loadedBy);

        try {
            loader.load();
        } catch (IOException e) {
            throw new ComponentLoaderException(e);
        }
    }
}
