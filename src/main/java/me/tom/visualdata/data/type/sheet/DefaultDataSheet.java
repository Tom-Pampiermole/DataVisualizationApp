package me.tom.visualdata.data.type.sheet;

import java.util.*;

public class DefaultDataSheet implements DataSheet {
    private final Map<String, List<DataSheetEntry<?>>> entriesByRow;
    public DefaultDataSheet() {
        entriesByRow = new HashMap<>();
    }

    @Override
    public Set<String> getRowNames() {
        return entriesByRow.keySet();
    }

    @Override
    public List<DataSheetEntry<?>> getRowOfIndex(int index) {
        Set<String> rowNames = getRowNames();
        return index < 0 || index >= rowNames.size() ? new ArrayList<>() : entriesByRow.get(rowNames.toArray()[index].toString());
    }

    @Override
    public List<DataSheetEntry<?>> getRow(String name) {
        return entriesByRow.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public Map<String, List<DataSheetEntry<?>>> getContents() {
        return new HashMap<>(entriesByRow);
    }
}
