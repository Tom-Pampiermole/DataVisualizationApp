package me.tom.visualdata.data.type.sheet.row;

import me.tom.visualdata.data.type.sheet.entry.DataSheetEntry;

import java.util.List;

public interface DataSheetRow<T> {

    /**
     *  Gets the name from the row
     *
     * @return Name from the row
     */
    String getName();

    /**
     *  Gets the generic type of each element stored in this row
     *
     * @return Generic type of row
     */
    Class<T> getType();

    /**
     *  Adds given {@link DataSheetEntry} to the row
     *
     * @param entry {@link DataSheetEntry} to be added to the row
     * @return True when added
     */
    boolean addEntry(DataSheetEntry<T> entry);

    /**
     *  Removes the {@link DataSheetEntry} at given index from the row
     *
     * @param index Index from to be removed {@link DataSheetEntry}
     * @return Removed {@link DataSheetEntry}
     */
    DataSheetEntry<T> removeEntry(int index);

    /**
     *  Gets the {@link DataSheetEntry} at given index from the row
     *
     * @param index Index from to be removed {@link DataSheetEntry}
     * @return Removed {@link DataSheetEntry}
     */
    DataSheetEntry<T> getEntry(int index);

    /**
     *  Gets a {@link List} of each {@link DataSheetEntry} from this row
     *
     * @return {@link List} of each {@link DataSheetEntry} from this row
     */
    List<DataSheetEntry<T>> getEntries();
}
