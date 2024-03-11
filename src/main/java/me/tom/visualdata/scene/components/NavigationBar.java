package me.tom.visualdata.scene.components;

import javafx.scene.layout.VBox;
import me.tom.visualdata.scene.components.loader.ComponentLoaderException;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

public class NavigationBar extends VBox {
    private static final String COMPONENT_NAME = "NavigationBar";


    public NavigationBar() throws ComponentLoaderException {
//        TODO Make class
        new FXMLComponentLoader().load(COMPONENT_NAME, this);

    }
}
