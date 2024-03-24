package me.tom.visualdata.scene.components.navigation;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import me.tom.visualdata.scene.components.ComponentException;
import me.tom.visualdata.scene.components.ComponentUtil;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationBarButton extends Button implements Initializable {
    private static final String COMPONENT_NAME = "navigation/NavigationBarButton";
    private static final int DETAILS_COLUMN_AMOUNT = 6;
    private static final int ICON_COLUMN_SPAN = 1;
    private static final int LABEL_COLUMN_SPAN = 5;
    private static final int LABEL_FONT_SIZE_DIVISION_FACTOR = 12;


    @FXML
    private ImageView icon;

    @FXML
    private Label label;

    @FXML
    private HBox details;

    private final StringProperty labelText = new SimpleStringProperty();

    public NavigationBarButton() throws ComponentException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);

        label.textProperty().bind(labelText);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComponentUtil.bindPrefSizeToActiveSceneOfRegion(this);
        details.setAlignment(Pos.CENTER);

        icon.fitWidthProperty().bind(details.widthProperty().divide(DETAILS_COLUMN_AMOUNT).multiply(ICON_COLUMN_SPAN));

        label.prefWidthProperty().bind(details.widthProperty().divide(DETAILS_COLUMN_AMOUNT).multiply(LABEL_COLUMN_SPAN));
        label.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", details.widthProperty().divide(LABEL_FONT_SIZE_DIVISION_FACTOR), ";"));
    }

    /**
     *  Sets the contents from the displayed text to given value
     *
     * @param labelText Contents of text to be displayed
     */
    @SuppressWarnings("unused")
    public void setLabelText(String labelText) {
        this.labelText.set(labelText == null ? "" : labelText);
    }

    /**
     *  Gets the contents of the currently displaying text
     *
     * @return Text currently being displayed
     */
    @SuppressWarnings("unused")
    public String getLabelText() {
        return labelText.get();
    }
}
