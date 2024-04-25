package me.tom.visualdata.data.type.sheet;

import me.tom.visualdata.data.type.sheet.entry.DataSheetEntry;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DataSheet {

    /**
     *  Adds a new row with given name
     *
     * @param name Name from the to be created row
     * @return True when created
     * @throws DataSheetException Thrown when row could not be added
     */
    boolean addRow(String name) throws DataSheetException;


    /**
     *  Gets a {@link Set} containing the name of each row
     *
     * @return {@link Set} containing the name of each row
     */
    Set<String> getRowNames();

    /**
     *  Gets a {@link List} containing each {@link DataSheetEntry} from row of given index
     *
     * @param index Index of row to retrieve {@link List} of {@link DataSheetEntry} of
     * @return {@link List} of each {@link DataSheetEntry} from row of given index
     */
    List<DataSheetEntry<?>> getRowOfIndex(int index);

    /**
     * Gets a {@link List} containing each {@link DataSheetEntry} from row of given name
     *
     * @param name Name of row to retrieve {@link List} of {@link DataSheetEntry} of
     * @return {@link List} of each {@link DataSheetEntry} from row of given index
     */
    List<DataSheetEntry<?>> getRow(String name);

    /**
     *  Gets all contents as a {@link Map} of a {@link List} containing each {@link DataSheetEntry} as value using the name as key
     *
     * @return {@link Map} of a {@link List} containing each {@link DataSheetEntry} as value using the name as key
     */
    Map<String, List<DataSheetEntry<?>>> getContents();

}
