package me.tom.visualdata.scene.components.data;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import me.tom.visualdata.data.type.table.SheetDataTableLoader;
import me.tom.visualdata.scene.components.ComponentException;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;

import java.net.URL;

public class DataTable extends TableView<ObservableList<String>> {
    private static final String COMPONENT_NAME = "data/DataTable";
    private static final boolean EDITABLE = true;


    public DataTable() throws ComponentException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);
        setEditable(EDITABLE);

        URL urlOfFile = getClass().getClassLoader().getResource("dummydata.xlsx");
        new SheetDataTableLoader(urlOfFile.getPath()).load(this);
    }

    public void addColumn(String name) {
        final int columnIndex = getColumns().size();
        TableColumn<ObservableList<String>, String> newColumn = new TableColumn<>(name);
        newColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().size() > columnIndex ? param.getValue().get(columnIndex) : ""));
        getColumns().add(newColumn);
        if(EDITABLE) {
            makeColumnEditable(newColumn);
        }

        addEmptyCellsInColumnForExistentRows();
    }

    /**
     *  Makes given {@link TableColumn} editable
     *
     * @param column {@link TableColumn} to be made editable
     */
    private void makeColumnEditable(TableColumn<ObservableList<String>, String> column) {
        assert EDITABLE : "Failed to make column editable: table not editable";
        assert column != null : "Failed to make column editable: column null";
        column.setCellFactory(TextFieldTableCell.forTableColumn());

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
