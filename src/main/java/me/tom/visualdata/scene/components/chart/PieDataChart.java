package me.tom.visualdata.scene.components.chart;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import me.tom.visualdata.scene.components.ComponentException;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

public class PieDataChart extends PieChart {
    private static final String COMPONENT_NAME = "chart/PieDataChart";


    private final ObservableList<PieChart.Data> pieChartData;

    public PieDataChart() throws ComponentException, InterruptedException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);

        pieChartData = FXCollections.observableArrayList();

        dataProperty().bind(Bindings.createObjectBinding(() -> pieChartData, pieChartData));
    }


    // Method to add data

}
