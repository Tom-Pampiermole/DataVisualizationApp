package me.tom.visualdata.scene.components.chart;

import javafx.scene.chart.PieChart;
import me.tom.visualdata.scene.components.ComponentException;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

public class PieDataChart extends PieChart {
    private static final String COMPONENT_NAME = "chart/PieDataChart";


    public PieDataChart() throws ComponentException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);

    }

}
