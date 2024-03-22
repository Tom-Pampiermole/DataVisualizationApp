module me.tom.visualdata.datavisualizationapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.tom.visualdata to javafx.fxml;
    opens me.tom.visualdata.scene.components.navigation to javafx.fxml;
    opens scene.fxml to javafx.fxml;
    opens scene.fxml.components.navigation to javafx.fxml;
    exports me.tom.visualdata;
    exports me.tom.visualdata.scene.components;
    exports me.tom.visualdata.scene.components.navigation;
}