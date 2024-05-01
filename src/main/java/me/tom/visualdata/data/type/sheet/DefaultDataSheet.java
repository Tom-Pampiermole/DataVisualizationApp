package me.tom.visualdata.data.type.sheet;

import me.tom.visualdata.data.type.sheet.column.DataSheetColumn;
import me.tom.visualdata.data.type.sheet.column.DataSheetColumnException;
import me.tom.visualdata.data.type.sheet.column.DefaultDataSheetColumn;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultDataSheet implements DataSheet {
    private static final Class<?> COLUMN_DEFAULT_CLASS = String.class;     // Default class to be used when adding a new row


    private final List<DataSheetColumn<?>> columns;
    private final System.Logger logger;
    public DefaultDataSheet() {
        logger = System.getLogger(getClass().getName());
        columns = new ArrayList<>();
    }

    @Override
    public boolean addColumn(String name) {
        try {
            return columns.add(new DefaultDataSheetColumn<>(COLUMN_DEFAULT_CLASS, name));
        } catch (DataSheetColumnException e) {
            logger.log(System.Logger.Level.ERROR, e.getMessage());
            return false;
        }
    }

    @Override
    public Set<String> getColumnNames() {
        return columns.stream()
                .map(DataSheetColumn::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public DataSheetColumn<?> getColumnOfIndex(int index) {
        if (isIndexOfRowOutOfBounds(index)) {
            return null;
        }

        return columns.get(index);
    }

    /**
     *  Gets whether given index is out of bounds for {@link #rows}
     *
     * @param index Index to be checked
     * @return {@link true} when index is out of bounds
     */
    private boolean isIndexOfRowOutOfBounds(int index) {
        return index < 0 || index >= columns.size();
    }

    @Override
    public DataSheetColumn<?> getColumn(String name) {
        return columns.stream()
                .filter(column -> column.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<DataSheetColumn<?>> getContents() {
        return new ArrayList<>(columns);
    }
}
