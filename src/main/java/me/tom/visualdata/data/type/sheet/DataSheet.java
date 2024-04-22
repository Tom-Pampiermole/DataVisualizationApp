package me.tom.visualdata.data.type.sheet;

import java.util.List;
import java.util.Map;

public interface DataSheet {
    /**
     *  Gets the name of every row or an empty list when no rows are defined.
     *
     * @return Name of every row
     */
    List<String> getRowNames();

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
