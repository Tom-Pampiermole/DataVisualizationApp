package me.tom.visualdata.data.type.sheet;

public class DefaultDataSheetEntry<T> implements DataSheetEntry<T> {
    private final Class<T> type;
    private final T value;
    public DefaultDataSheetEntry(Class<T> type, T value) throws DataSheetException {
        if(type == null) {
            throw new DataSheetException("Cannot instantiate: type null");
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
