package me.tom.visualdata.scene.components.data;

import javafx.scene.control.TableView;
import me.tom.visualdata.scene.components.loader.ComponentLoaderException;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

public class DataTable extends TableView<String> {
    private static final String COMPONENT_NAME = "chart/PieDataChart";

    public DataTable() throws ComponentLoaderException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);

    }


}
