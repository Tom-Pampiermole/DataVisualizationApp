package me.tom.visualdata.scene.loader;

import javafx.scene.Scene;

public interface SceneLoader {
    /**
     *  Loads a new {@link Scene} with given name and given size
     *
     * @param name Name from the scene to load
     * @param width Width from the scene
     * @param height Height from the scene
     * @return Loaded {@link Scene}
     *
     * @throws SceneLoaderException Thrown when scene could not be loaded
     */
    Scene load(String name, int width, int height) throws SceneLoaderException;
}
