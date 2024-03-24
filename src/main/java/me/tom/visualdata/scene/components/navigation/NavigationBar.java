package me.tom.visualdata.scene.components.navigation;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import me.tom.visualdata.scene.components.ComponentException;
import me.tom.visualdata.scene.components.ComponentUtil;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationBar extends VBox implements Initializable {
    private static final String COMPONENT_NAME = "navigation/NavigationBar";
    private static final int MAX_VISIBLE_ROWS = 12; /* Amount of items visible on the vertical axis */


    public NavigationBar() throws ComponentException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVgrow(this, Priority.ALWAYS);

        bindChildRegionsHeightToRowHeight();

        ComponentUtil.bindPrefSizeToActiveSceneOfRegion(this, Scene::widthProperty, scene -> scene.heightProperty().divide(3));
    }

    /**
     *  Binds the maximum height of every child of type {@link Region} to the height of the navigation bar divided by
     *  {@link #MAX_VISIBLE_ROWS}
     */
    private void bindChildRegionsHeightToRowHeight() {
        for(Node child : getChildren()) {
            if(child instanceof Region childRegion) {
                childRegion.maxHeightProperty().bind(heightProperty().divide(MAX_VISIBLE_ROWS));
            }
        }
    }


}
