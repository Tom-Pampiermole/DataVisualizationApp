package me.tom.visualdata.scene.components.chart;

import javafx.scene.chart.Chart;
import me.tom.visualdata.scene.components.ComponentException;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

public class DataChart extends Chart {
    private static final String COMPONENT_NAME = "chart/DataChart";


    public DataChart() throws ComponentException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);

    }
    @Override
    protected void layoutChartChildren(double v, double v1, double v2, double v3) {

    }
}
