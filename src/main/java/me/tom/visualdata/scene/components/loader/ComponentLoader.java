package me.tom.visualdata.scene.components.loader;

public interface ComponentLoader {

    /**
     *  Loads a new component from given name and sets it up for {@link Object} {@param loadedBy}
     *
     * @param name Name from component to load
     * @param loadedBy Instance loading the component
     *
     * @throws ComponentLoaderException Thrown when component could not be loaded
     */
    void load(String name, Object loadedBy) throws ComponentLoaderException;
}
