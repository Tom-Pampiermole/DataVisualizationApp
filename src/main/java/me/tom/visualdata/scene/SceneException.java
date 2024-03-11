package me.tom.visualdata.scene;

import me.tom.visualdata.VisualizationAppException;

public class SceneException extends VisualizationAppException {
    protected SceneException(String message) {
        super(message);
    }

    protected SceneException(Exception exception) {
        super(exception);
    }
}
