package me.tom.visualdata.data.type.sheet;

import me.tom.visualdata.data.type.sheet.column.DataSheetColumn;

import java.util.List;
import java.util.Set;

public interface DataSheet {

    /**
     *  Adds a new row with given name
     *
     * @param name Name from the to be created column
     * @return True when created
     */
    boolean addColumn(String name);


    /**
     *  Gets a {@link Set} containing the name of each column
     *
     * @return {@link Set} containing the name of each column
     */
    Set<String> getColumnNames();

    /**
     *  Gets the {@link DataSheetColumn} of given index
     *
     * @param index Index of to be retrieved {@link DataSheetColumn}
     * @return {@link DataSheetColumn} of given index or null
     */
    DataSheetColumn<?> getColumnOfIndex(int index);

    /**
     *  Gets the {@link DataSheetColumn} of given index
     *
     * @param name Name of to be retrieved {@link DataSheetColumn}
     * @return {@link DataSheetColumn} of given name or null
     */
    DataSheetColumn<?> getColumn(String name);

    /**
     *  Gets all contents of the sheet
     *
     * @return Contents of sheet as {@link List} of each {@link DataSheetColumn}
     */
    List<DataSheetColumn<?>> getContents();

}
