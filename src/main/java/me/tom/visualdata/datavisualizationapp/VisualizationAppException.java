package me.tom.visualdata.datavisualizationapp;

public class VisualizationAppException extends Exception {
    public VisualizationAppException(String message) {
        super(message);
    }

    public VisualizationAppException(Exception exception) {
        super(exception);
    }
}
