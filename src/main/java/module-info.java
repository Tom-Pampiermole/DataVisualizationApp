module me.tom.visualdata.datavisualizationapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;


    opens me.tom.visualdata to javafx.fxml;
    opens me.tom.visualdata.scene.components.navigation to javafx.fxml;
    opens me.tom.visualdata.scene.components.chart to javafx.fxml;
    opens me.tom.visualdata.scene.components.data to javafx.fxml;

    opens scene.fxml to javafx.fxml;
    opens scene.fxml.components.navigation to javafx.fxml;


    exports me.tom.visualdata;
    exports me.tom.visualdata.scene.components;
    exports me.tom.visualdata.scene.components.navigation;
    exports me.tom.visualdata.scene.components.chart;
}