package me.tom.visualdata.scene.components.data;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import me.tom.visualdata.data.type.table.SheetDataTableLoader;
import me.tom.visualdata.scene.components.loader.ComponentLoaderException;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

import java.net.URL;

public class DataTable extends TableView<ObservableList<String>> {
    private static final String COMPONENT_NAME = "data/DataTable";

    public DataTable() throws ComponentLoaderException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);

        URL urlOfFile = getClass().getClassLoader().getResource("dummydata.xlsx");
        new SheetDataTableLoader(urlOfFile.getPath()).load(this);
    }

    public void addColumn(String name) {
        final int columnIndex = getColumns().size();
        TableColumn<ObservableList<String>, String> newColumn = new TableColumn<>(name);
        newColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().size() > columnIndex ? param.getValue().get(columnIndex) : ""));
        getColumns().add(newColumn);

        addEmptyCellsInColumnForExistentRows();
    }

    /**
     *  Adds empty cells for each row that has fewer columns than {@link #getColumns().size() }
     */
    private void addEmptyCellsInColumnForExistentRows() {
        final int amountOfColumns = getColumns().size();
        for (ObservableList<String> row : getItems()) {
            while (row.size() <= amountOfColumns) {
                row.add("");
            }
        }
    }


    /**
     *  Creates an empty row in the table
     *
     * @return Created row
     */
    public ObservableList<String> createEmptyRow() {
        ObservableList<String> row = FXCollections.observableArrayList();

        // Seed row
        int amountOfColumns = getColumns().size();
        for (int i = 0; i < amountOfColumns; i++) {
            row.add("");
        }

        getItems().add(row);
        return row;
    }
}
