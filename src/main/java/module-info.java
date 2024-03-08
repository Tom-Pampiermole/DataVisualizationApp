module me.tom.visualdata.datavisualizationapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.tom.visualdata.datavisualizationapp to javafx.fxml;
    exports me.tom.visualdata.datavisualizationapp;
}