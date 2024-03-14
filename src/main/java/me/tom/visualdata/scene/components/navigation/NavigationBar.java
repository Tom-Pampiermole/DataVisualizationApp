package me.tom.visualdata.scene.components.navigation;

import javafx.fxml.Initializable;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import me.tom.visualdata.scene.components.ComponentException;
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

        sceneProperty().addListener((observable, oldScene, newScene) -> {
            if(newScene != null) {
                prefHeightProperty().bind(newScene.heightProperty());
                prefWidthProperty().bind(newScene.widthProperty().divide(5));
            }
        });
    }


}
