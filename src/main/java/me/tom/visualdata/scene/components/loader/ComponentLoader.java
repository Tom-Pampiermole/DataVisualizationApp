package me.tom.visualdata.scene.components.loader;

import me.tom.visualdata.scene.components.ComponentException;

public interface ComponentLoader {

    /**
     *  Loads a new component from given name and sets it up for {@link Class} {@param loadedBy}
     *
     * @param name Name from component to load
     * @param loadedBy Class loading the component
     *
     * @throws ComponentException Thrown when component could not be loaded
     */
    void load(String name, Class<?> loadedBy) throws ComponentException;
}
