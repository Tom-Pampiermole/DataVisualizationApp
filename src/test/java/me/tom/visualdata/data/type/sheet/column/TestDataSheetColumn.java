package me.tom.visualdata.data.type.sheet.column;

import me.tom.visualdata.data.type.sheet.entry.DataSheetEntry;
import me.tom.visualdata.data.type.sheet.entry.DataSheetEntryException;
import me.tom.visualdata.data.type.sheet.entry.DefaultDataSheetEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataSheetColumn {
    private static final String DATASHEET_COLUMN_CLASS_NAME = DefaultDataSheetColumn.class.getSimpleName();
    private static final String DATASHEET_COLUMN_NAME = "Foo";
    private static final Class<?> DATASHEET_COLUMN_GENERIC_TYPE = String.class;

    private static final String DATASHEET_ENTRY_VALUE = "Bar";
    private static final Class<?> DATASHEET_VALUE_GENERIC_TYPE = String.class;


    private DataSheetColumn<?> column;
    private DataSheetEntry<String> entry;

    @BeforeEach
    void setupBeforeEach() throws DataSheetEntryException, DataSheetColumnException {
        entry = createDataSheetEntry();
        column = new DefaultDataSheetColumn<>(String.class, DATASHEET_COLUMN_NAME);
    }

    /**
     *  Creates a new {@link DataSheetEntry} using {@link String} as type and {@link #DATASHEET_ENTRY_VALUE} as value
     *
     * @return Created {@link DataSheetEntry}
     * @throws DataSheetEntryException Thrown when instantiation failed
     */
    private DataSheetEntry<String> createDataSheetEntry() throws DataSheetEntryException {
        return new DefaultDataSheetEntry<>(String.class, DATASHEET_ENTRY_VALUE);
    }


    @Test
    void given_WhenInstantiate_GivenTypeNull_ExpectedException() {
        assertThrows(DataSheetColumnException.class, () -> new DefaultDataSheetColumn<>(null, DATASHEET_COLUMN_NAME));
    }

    @Test
    void given_WhenInstantiate_TypeObject_ExpectedMatches() throws DataSheetColumnException {
        column = new DefaultDataSheetColumn<>(Object.class, DATASHEET_COLUMN_NAME);
        assertEquals(Object.class, column.getType());
    }

    @Test
    void given_WhenInstantiate_TypeString_ExpectedMatches() throws DataSheetColumnException {
        column = new DefaultDataSheetColumn<>(String.class, DATASHEET_COLUMN_NAME);
        assertEquals(DATASHEET_VALUE_GENERIC_TYPE, column.getType());
    }

    @Test
    void given_WhenInstantiate_NameNull_ExpectedMatches() throws DataSheetColumnException {
        column = new DefaultDataSheetColumn<>(String.class, null);
        assertNull(column.getName());
    }

    @Test
    void given_WhenInstantiate_WhenNameFoo_ExpectedMatches() {
        assertEquals(DATASHEET_COLUMN_NAME, column.getName());
    }

    @Test
    void givenColumn_WhenAddEntry_EntryNull_ExpectedNotAdded() {
        assertFalse(column.addEntry(null));
        assertColumnContainsElements();
    }

    /**
     *  Asserts whether {@link DataSheetColumn} {@link #column} contains every given {@link DataSheetEntry}
     *
     * @param entries {@link DataSheetEntry} to be contained by the {@link DataSheetColumn}
     */
    private void assertColumnContainsElements(DataSheetEntry<?>... entries) {
        assertEquals(entries.length, column.getEntries().size());
        assertEquals(List.of(entries), column.getEntries());
    }

    @Test
    void givenEmptyColumn_WhenAddEntry_ExpectedAdded() {
        assertTrue(column.getEntries().isEmpty());
        addEntryToColumn(entry, true);
    }

    /**
     *  Adds given {@link DataSheetEntry} to the {@link DataSheetColumn} {@link #column}
     *
     * @param entryToAdd {@link DataSheetEntry} to be added to the {@link DataSheetColumn}
     * @param expectedAdded Whether it is expected that given {@link DataSheetEntry} has been added
     */
    @SuppressWarnings({"unchecked"})
    private void addEntryToColumn(DataSheetEntry<String> entryToAdd, boolean expectedAdded) {
        DataSheetColumn<String> column = (DataSheetColumn<String>)this.column;
        List<DataSheetEntry<String>> oldEntries = column.getEntries();
        if(expectedAdded) {
            oldEntries.add(entryToAdd);
        }

        assertEquals(expectedAdded, column.addEntry(entryToAdd));
        assertColumnContainsElements(oldEntries.toArray(new DataSheetEntry[0]));
    }

    @Test
    void givenColumnWithEntry_WhenAddEntry_EntryAlreadyPresent_ExpectedNotAdded() {
        addEntryToColumn(entry, true);
        addEntryToColumn(entry, false);
    }

    @Test
    void givenColumnWithEntry_WhenAddNewEntry_ExpectedAdded() throws DataSheetEntryException {
        addEntryToColumn(entry, true);

        DataSheetEntry<String> newEntry = createDataSheetEntry();
        addEntryToColumn(newEntry, true);
    }

    @Test
    void givenEmptyColumn_WhenRemoveEntry_Index0_ExpectedNull() {
        assertColumnContainsElements();
        assertNull(column.removeEntry(0));
    }

    @Test
    void givenColumnWithEntry_WhenRemoveEntry_Index1_ExpectedNull() {
        addEntryToColumn(entry, true);
        assertNull(column.removeEntry(1));
    }

    @Test
    void givenColumnWithEntry_WhenRemoveEntry_Index0_ExpectedRemoved() {
        addEntryToColumn(entry, true);
        assertEquals(entry, column.removeEntry(0));
        assertColumnContainsElements();
    }

    @Test
    void givenColumnWithTwoEntries_WhenRemoveEntry_Index0_ExpectedRemoved() throws DataSheetEntryException {
        DataSheetEntry<String> secondEntry = createDataSheetEntry();
        addEntryToColumn(entry, true);
        addEntryToColumn(secondEntry, true);

        assertEquals(entry, column.removeEntry(0));
        assertColumnContainsElements(secondEntry);
    }

    @Test
    void givenEmptyColumn_WhenGetEntry_Index0_ExpectedNull() {
        assertColumnContainsElements();
        assertNull(column.getEntry(0));
    }

    @Test
    void givenColumnWithEntry_WhenGetEntry_Index1_ExpectedNull() {
        addEntryToColumn(entry, true);
        assertNull(column.getEntry(1));
    }

    @Test
    void givenColumnWithEntry_WhenGetEntry_Index0_ExpectedMatches() {
        addEntryToColumn(entry, true);
        assertEquals(entry, column.getEntry(0));
    }

    @Test
    void givenColumnWithTwoEntries_WhenGetEntry_Index1_ExpectedMatches() throws DataSheetEntryException {
        DataSheetEntry<String> secondEntry = createDataSheetEntry();
        addEntryToColumn(entry, true);
        addEntryToColumn(secondEntry, true);
        assertEquals(secondEntry, column.getEntry(1));
    }

    @SuppressWarnings("unchecked")
    @Test
    void givenEmptyColumn_WhenAddToGetEntries_ExpectedNotAddedToColumn() {
        assertColumnContainsElements();
        assertTrue(((DataSheetColumn<String>) column).getEntries().add(entry));
        assertColumnContainsElements();
    }


    @Test
    void givenEmptyColumn_WhenToString_ExpectedToStringMatches() {
        assertToStringMatches("");
    }

    @Test
    void givenColumnWithEntries_WhenToString_ExpectedMatches() {
        addEntryToColumn(entry, true);
        assertToStringMatches("\n" + entry.toString());
    }


    /**
     * Asserts whether {@link #entry#toString()} matches a string built of given parameters
     *
     * @param entries Expected entries of {@link DataSheetColumn}
     */
    private void assertToStringMatches(String entries) {
        assertEquals(String.format("%s: {\n\"type\": %s,\n\"entries\": [%s]\n}", DATASHEET_COLUMN_CLASS_NAME, DATASHEET_COLUMN_GENERIC_TYPE, entries), column.toString());
    }

}
