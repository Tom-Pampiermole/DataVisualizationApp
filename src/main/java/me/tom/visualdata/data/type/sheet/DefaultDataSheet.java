package me.tom.visualdata.data.type.sheet;

import me.tom.visualdata.data.type.sheet.row.DataSheetRow;
import me.tom.visualdata.data.type.sheet.row.DataSheetRowException;
import me.tom.visualdata.data.type.sheet.row.DefaultDataSheetRow;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultDataSheet implements DataSheet {
    private static final Class<?> ROW_DEFAULT_CLASS = String.class;     // Default class to be used when adding a new row


    private final List<DataSheetRow<?>> rows;
    private final System.Logger logger;
    public DefaultDataSheet() {
        logger = System.getLogger(getClass().getName());
        rows = new ArrayList<>();
    }

    @Override
    public boolean addRow(String name) {
        try {
            return rows.add(new DefaultDataSheetRow<>(ROW_DEFAULT_CLASS, name));
        } catch (DataSheetRowException e) {
            logger.log(System.Logger.Level.ERROR, e.getMessage());
            return false;
        }
    }

    @Override
    public Set<String> getRowNames() {
        return rows.stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
    }

    @Override
    public DataSheetRow<?> getRowOfIndex(int index) {
        if (isIndexOfRowOutOfBounds(index)) {
            return null;
        }

        return rows.get(index);
    }

    /**
     *  Gets whether given index is out of bounds for {@link #rows}
     *
     * @param index Index to be checked
     * @return {@link true} when index is out of bounds
     */
    private boolean isIndexOfRowOutOfBounds(int index) {
        return index < 0 || index >= rows.size();
    }

    @Override
    public DataSheetRow<?> getRow(String name) {
        return rows.stream()
                .filter(row -> row.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<DataSheetRow<?>> getContents() {
        return new ArrayList<>(rows);
    }
}
