package me.tom.visualdata.data.type.sheet.entry;

public interface DataSheetEntry<T> {

    /**
     *  Gets the value of the entry
     * @return Value of entry
     */
    T getValue();

    /**
     * Gets the generic type of the entry
     * @return Generic class type of entry
     */
    Class<T> getType();
}
