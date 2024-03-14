package me.tom.visualdata.scene.components;

import javafx.scene.layout.Region;

public class ComponentUtil {
    private ComponentUtil() { }


    /**
     *  Binds the preferred size properties of given {@link Region} to the size of parent scene to be updated when the
     *  parent scene changes.
     *
     * @param region {@link Region} to bind its' size properties of
     */
    public static void bindPrefSizeToActiveSceneOfRegion(Region region) {
        assert region != null : "Cannot bind preferred size of region to active scene: region null";
        region.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if(newScene != null) {
                region.prefHeightProperty().bind(newScene.heightProperty());
                region.prefWidthProperty().bind(newScene.widthProperty().divide(5));
            }
        });
    }

}
