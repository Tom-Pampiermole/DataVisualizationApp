package me.tom.visualdata.scene.components.navigation;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import me.tom.visualdata.scene.components.ComponentException;
import me.tom.visualdata.scene.components.ComponentUtil;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationBarButton extends Button implements Initializable {
    private static final String COMPONENT_NAME = "navigation/NavigationBarButton";


    public NavigationBarButton() throws ComponentException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComponentUtil.bindPrefSizeToActiveSceneOfRegion(this);
    }
}
