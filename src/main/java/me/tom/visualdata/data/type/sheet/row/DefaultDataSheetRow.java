package me.tom.visualdata.data.type.sheet.row;

import me.tom.visualdata.data.type.sheet.entry.DataSheetEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return name;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public boolean addEntry(DataSheetEntry<T> entry) {
        if(entry == null) {
            return false;
        }
        if(entries.contains(entry)) {
            return false;
        }

        return entries.add(entry);
    }

    @Override
    public DataSheetEntry<T> removeEntry(int index) {
        if(isIndexOutOfEntriesBounds(index)) {
            return null;
        }

        return entries.remove(index);
    }

    /**
     *  Gets whether given index is outside of bounds of {@link #entries}
     *
     * @param index Index to be validated
     * @return {@link true} when {@param index} is out of bounds
     */
    private boolean isIndexOutOfEntriesBounds(int index) {
        return index < 0|| index >= entries.size();
    }

    @Override
    public DataSheetEntry<T> getEntry(int index) {
        if(isIndexOutOfEntriesBounds(index)) {
            return null;
        }

        return entries.get(index);
    }

    @Override
    public List<DataSheetEntry<T>> getEntries() {
        return new ArrayList<>(entries);
    }

    @Override
    public String toString() {
        return String.format("%s: {\n\"type\": %s,\n\"entries\": [%s]\n}",
                getClass().getSimpleName(),
                type,
                entries.isEmpty() ? "" : "\n" + entries.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")));
    }
}
