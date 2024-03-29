package me.tom.visualdata.scene.loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class FXMLSceneLoader implements SceneLoader {
    @Override
    public Scene load(String name, int width, int height) throws SceneLoaderException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(String.format("/scene/fxml/%s.fxml", name)));
            return new Scene(fxmlLoader.load(), width, height);
        } catch(IOException e) {
            throw new SceneLoaderException(e);
        }
    }
}
