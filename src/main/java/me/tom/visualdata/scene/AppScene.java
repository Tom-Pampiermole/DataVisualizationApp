package me.tom.visualdata.scene;

import javafx.scene.Scene;
import javafx.stage.Stage;
import me.tom.visualdata.scene.loader.FXMLSceneLoader;
import me.tom.visualdata.scene.loader.SceneLoader;

public enum AppScene {
    DASHBOARD("dashboard")
    ;


    private final Scene scene;
    AppScene(String name) {
        try {
            scene = Defaults.SCENE_LOADER.load(name, Defaults.SCENE_WIDTH, Defaults.SCENE_HEIGHT);
        } catch(Exception exception) {
            throw new IllegalStateException(exception);
        }
    }

    /**
     *  Makes this scene the currently active scene from given {@link Stage}
     *
     * @param stage {@link Stage} to set the active scene of
     */
    public void makeActive(Stage stage) {
        assert stage != null : String.format("Cannot make %s active: stage null", getClass().getSimpleName());
        stage.setScene(scene);
    }


    /**
     *  Interface to contain default values from enum due to the inability of
     */
    private interface Defaults {
        SceneLoader SCENE_LOADER = new FXMLSceneLoader();
        int SCENE_WIDTH = 1200;
        int SCENE_HEIGHT = 650;
    }
}
