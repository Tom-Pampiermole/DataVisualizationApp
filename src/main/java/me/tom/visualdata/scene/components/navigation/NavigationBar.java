package me.tom.visualdata.scene.components.navigation;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import me.tom.visualdata.scene.components.ComponentException;
import me.tom.visualdata.scene.components.ComponentUtil;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationBar extends VBox implements Initializable {
    private static final String COMPONENT_NAME = "navigation/NavigationBar";


    public NavigationBar() throws ComponentException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVgrow(this, Priority.ALWAYS);

        ComponentUtil.bindPrefSizeToActiveSceneOfRegion(this, Scene::widthProperty, scene -> scene.heightProperty().divide(3));
    }


}
