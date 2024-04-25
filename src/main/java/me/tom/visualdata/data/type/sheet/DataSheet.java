package me.tom.visualdata.data.type.sheet;

import me.tom.visualdata.data.type.sheet.entry.DataSheetEntry;
import me.tom.visualdata.data.type.sheet.row.DataSheetRow;

import java.util.List;
import java.util.Set;

public interface DataSheet {

    /**
     *  Adds a new row with given name
     *
     * @param name Name from the to be created row
     * @return True when created
     */
    boolean addRow(String name);


    /**
     *  Gets a {@link Set} containing the name of each row
     *
     * @return {@link Set} containing the name of each row
     */
    Set<String> getRowNames();

    /**
     *  Gets the {@link DataSheetRow} of given index
     *
     * @param index Index of to be retrieved {@link DataSheetRow}
     * @return {@link DataSheetRow} of given index or null
     */
    DataSheetRow<?> getRowOfIndex(int index);

    /**
     * Gets a {@link List} containing each {@link DataSheetEntry} from row of given name
     *
     * @param name Name of row to retrieve {@link List} of {@link DataSheetEntry} of
     * @return {@link List} of each {@link DataSheetEntry} from row of given index
     */

    /**
     *  Gets the {@link DataSheetRow} of given index
     *
     * @param name Name of to be retrieved {@link DataSheetRow}
     * @return {@link DataSheetRow} of given name or null
     */
    DataSheetRow<?> getRow(String name);

    /**
     *  Gets all contents of the sheet
     *
     * @return Contents of sheet as {@link List} of each {@link DataSheetRow}
     */
    List<DataSheetRow<?>> getContents();

}
