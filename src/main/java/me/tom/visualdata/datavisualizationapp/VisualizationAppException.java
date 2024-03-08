package me.tom.visualdata.datavisualizationapp;

public class VisualizationAppException extends Exception {
    protected VisualizationAppException(String message) {
        super(message);
    }

    protected VisualizationAppException(Exception exception) {
        super(exception);
    }
}
