package me.tom.visualdata.data.type.sheet.entry;

public class DefaultDataSheetEntry<T> implements DataSheetEntry<T> {
    private final Class<T> type;
    private final T value;
    public DefaultDataSheetEntry(Class<T> type, T value) throws DataSheetEntryException {
        if(type == null) {
            throw new DataSheetEntryException("Cannot instantiate: type null");
        }

        this.type = type;
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Class<T> getType() {
        return type;
    }


    @Override
    public String toString() {
        return String.format("%s: {\n\"type\": %s,\n\"value\": %s\n}", getClass().getSimpleName(), getValue(), getType());
    }
}
