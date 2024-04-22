package me.tom.visualdata.data.type.sheet.row;

import me.tom.visualdata.data.type.sheet.entry.DataSheetEntry;

import java.util.ArrayList;
import java.util.List;

public class DefaultDataSheetRow<T> implements DataSheetRow<T> {
    private final Class<T> type;
    private final String name;
    private final List<DataSheetEntry<T>> entries;

    public DefaultDataSheetRow(Class<T> type, String name) throws DataSheetRowException {
        if(type == null) {
            throw new DataSheetRowException("Cannot instantiate: type null");
        }

        this.type = type;
        this.name = name;
        this.entries = new ArrayList<>();
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public Class<T> getType() {
        return null;
    }

    @Override
    public boolean addEntry(DataSheetEntry<T> entry) {
        return false;
    }

    @Override
    public DataSheetEntry<T> removeEntry(int index) {
        return null;
    }

    @Override
    public DataSheetEntry<T> getEntry(int index) {
        return null;
    }

    @Override
    public List<DataSheetEntry<T>> getEntries() {
        return null;
    }

    @Override
    public String toString() {
        // Format String.format("%s: {\n\"type\": %s,\n\"entries\": [%s]\n}", DATASHEET_ROW_CLASS_NAME, genericType, entries), entry.toString());
        return super.toString();
    }
}
