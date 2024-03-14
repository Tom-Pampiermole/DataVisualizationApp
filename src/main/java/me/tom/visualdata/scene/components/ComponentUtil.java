package me.tom.visualdata.scene.components;

import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

import java.util.function.Function;

public class ComponentUtil {
    private ComponentUtil() { }

    /**
     *  Binds the preferred size properties of given {@link Region} to the size of parent scene to be updated when the
     *  parent scene changes.
     *
     * @param region {@link Region} to bind its' size properties of
     */
    public static void bindPrefSizeToActiveSceneOfRegion(Region region) {
        bindPrefSizeToActiveSceneOfRegion(region, Scene::widthProperty, Scene::heightProperty);
    }

    /**
     *  Binds the preferred size properties of given {@link Region} to the size of parent scene to be updated when the
     *  parent scene changes. Given functions are applied to mutate the value from the width and height properties
     *
     * @param region {@link Region} to bind its' size properties of
     * @param widthPropertyFunction Function to be used to bind the width property to
     * @param heightPropertyFunction Function to be used to bind the height property to
     */
    public static void bindPrefSizeToActiveSceneOfRegion(Region region,
                                                         Function<Scene, ObservableValue<? extends Number>> widthPropertyFunction,
                                                         Function<Scene, ObservableValue<? extends Number>> heightPropertyFunction) {
        assert region != null : "Cannot bind preferred size of region to active scene: region null";
        region.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if(newScene != null) {
                region.prefHeightProperty().bind(widthPropertyFunction.apply(newScene));
                region.prefWidthProperty().bind(heightPropertyFunction.apply(newScene));
            }
        });
    }

}
