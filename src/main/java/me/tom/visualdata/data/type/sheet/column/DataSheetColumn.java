package me.tom.visualdata.data.type.sheet.column;

import me.tom.visualdata.data.type.sheet.entry.DataSheetEntry;

import java.util.List;

public interface DataSheetColumn<T> {

    /**
     *  Gets the name from the column
     *
     * @return Name from the column
     */
    String getName();

    /**
     *  Gets the generic type of each element stored in this column
     *
     * @return Generic type of column
     */
    Class<T> getType();

    /**
     *  Adds given {@link DataSheetEntry} to the column
     *
     * @param entry {@link DataSheetEntry} to be added to the column
     * @return True when added
     */
    boolean addEntry(DataSheetEntry<T> entry);

    /**
     *  Removes the {@link DataSheetEntry} at given index from the column
     *
     * @param index Index from to be removed {@link DataSheetEntry}
     * @return Removed {@link DataSheetEntry}
     */
    DataSheetEntry<T> removeEntry(int index);

    /**
     *  Gets the {@link DataSheetEntry} at given index from the column
     *
     * @param index Index from to be removed {@link DataSheetEntry}
     * @return Removed {@link DataSheetEntry}
     */
    DataSheetEntry<T> getEntry(int index);

    /**
     *  Gets a {@link List} of each {@link DataSheetEntry} from this column
     *
     * @return {@link List} of each {@link DataSheetEntry} from this column
     */
    List<DataSheetEntry<T>> getEntries();
}
