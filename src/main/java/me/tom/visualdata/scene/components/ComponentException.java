package me.tom.visualdata.scene.components;

import me.tom.visualdata.scene.SceneException;

public class ComponentException extends SceneException {
    protected ComponentException(Exception exception) {
        super(exception);
    }
}
