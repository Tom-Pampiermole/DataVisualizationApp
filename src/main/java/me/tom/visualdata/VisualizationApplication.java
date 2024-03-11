package me.tom.visualdata;

import javafx.application.Application;
import javafx.stage.Stage;
import me.tom.visualdata.scene.AppScene;

public class VisualizationApplication extends Application {
    private static final String APP_TITLE = "Visualization Application";

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        AppScene.DASHBOARD.makeActive(stage);
        stage.setTitle(APP_TITLE);
        stage.show();

    }

}
