package me.tom.visualdata.data.type.sheet.row;

import me.tom.visualdata.data.type.sheet.entry.DataSheetEntry;
import me.tom.visualdata.data.type.sheet.entry.DataSheetEntryException;
import me.tom.visualdata.data.type.sheet.entry.DefaultDataSheetEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataSheetRow {
    private static final String DATASHEET_ROW_CLASS_NAME = DefaultDataSheetRow.class.getSimpleName();
    private static final String DATASHEET_ROW_NAME = "Foo";
    private static final Class<?> DATASHEET_ROW_GENERIC_TYPE = String.class;

    private static final String DATASHEET_ENTRY_VALUE = "Bar";
    private static final Class<?> DATASHEET_VALUE_GENERIC_TYPE = String.class;


    private DataSheetRow<?> row;
    private DataSheetEntry<String> entry;

    @BeforeEach
    void setupBeforeEach() throws DataSheetEntryException, DataSheetRowException {
        entry = createDataSheetEntry();
        row = new DefaultDataSheetRow<>(String.class, DATASHEET_ROW_NAME);
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
        assertThrows(DataSheetRowException.class, () -> new DefaultDataSheetRow<>(null, DATASHEET_ROW_NAME));
    }

    @Test
    void given_WhenInstantiate_TypeObject_ExpectedMatches() throws DataSheetRowException {
        row = new DefaultDataSheetRow<>(Object.class, DATASHEET_ROW_NAME);
        assertEquals(Object.class, row.getType());
    }

    @Test
    void given_WhenInstantiate_TypeString_ExpectedMatches() throws DataSheetRowException {
        row = new DefaultDataSheetRow<>(String.class, DATASHEET_ROW_NAME);
        assertEquals(DATASHEET_VALUE_GENERIC_TYPE, row.getType());
    }

    @Test
    void given_WhenInstantiate_NameNull_ExpectedMatches() throws DataSheetRowException {
        row = new DefaultDataSheetRow<>(String.class, null);
        assertNull(row.getName());
    }

    @Test
    void given_WhenInstantiate_WhenNameFoo_ExpectedMatches() {
        assertEquals(DATASHEET_ROW_NAME, row.getName());
    }

    @Test
    void givenRow_WhenAddEntry_EntryNull_ExpectedNotAdded() {
        assertFalse(row.addEntry(null));
        assertRowContainsElements();
    }

    /**
     *  Asserts whether {@link DataSheetRow} {@link #row} contains every given {@link DataSheetEntry}
     *
     * @param entries {@link DataSheetEntry} to be contained by the {@link DataSheetRow}
     */
    private void assertRowContainsElements(DataSheetEntry<?>... entries) {
        assertEquals(entries.length, row.getEntries().size());
        assertEquals(List.of(entries), row.getEntries());
    }

    @Test
    void givenEmptyRow_WhenAddEntry_ExpectedAdded() {
        assertTrue(row.getEntries().isEmpty());
        addEntryToRow(entry, true);
    }

    /**
     *  Adds given {@link DataSheetEntry} to the {@link DataSheetRow} {@link #row}
     *
     * @param entryToAdd {@link DataSheetEntry} to be added to the {@link DataSheetRow}
     * @param expectedAdded Whether it is expected that given {@link DataSheetEntry} has been added
     */
    @SuppressWarnings({"unchecked"})
    private void addEntryToRow(DataSheetEntry<String> entryToAdd, boolean expectedAdded) {
        DataSheetRow<String> row = (DataSheetRow<String>)this.row;
        List<DataSheetEntry<String>> oldEntries = row.getEntries();
        if(expectedAdded) {
            oldEntries.add(entryToAdd);
        }

        assertEquals(expectedAdded, row.addEntry(entryToAdd));
        assertRowContainsElements(oldEntries.toArray(new DataSheetEntry[0]));
    }

    @Test
    void givenRowWithEntry_WhenAddEntry_EntryAlreadyPresent_ExpectedNotAdded() {
        addEntryToRow(entry, true);
        addEntryToRow(entry, false);
    }

    @Test
    void givenRowWithEntry_WhenAddNewEntry_ExpectedAdded() throws DataSheetEntryException {
        addEntryToRow(entry, true);

        DataSheetEntry<String> newEntry = createDataSheetEntry();
        addEntryToRow(newEntry, true);
    }

    @Test
    void givenEmptyRow_WhenRemoveEntry_Index0_ExpectedNull() {
        assertRowContainsElements();
        assertNull(row.removeEntry(0));
    }

    @Test
    void givenRowWithEntry_WhenRemoveEntry_Index1_ExpectedNull() {
        addEntryToRow(entry, true);
        assertNull(row.removeEntry(1));
    }

    @Test
    void givenRowWithEntry_WhenRemoveEntry_Index0_ExpectedRemoved() {
        addEntryToRow(entry, true);
        assertEquals(entry, row.removeEntry(0));
        assertRowContainsElements();
    }

    @Test
    void givenRowWithTwoEntries_WhenRemoveEntry_Index0_ExpectedRemoved() throws DataSheetEntryException {
        DataSheetEntry<String> secondEntry = createDataSheetEntry();
        addEntryToRow(entry, true);
        addEntryToRow(secondEntry, true);

        assertEquals(entry, row.removeEntry(0));
        assertRowContainsElements(secondEntry);
    }

    @Test
    void givenEmptyRow_WhenGetEntry_Index0_ExpectedNull() {
        assertRowContainsElements();
        assertNull(row.getEntry(0));
    }

    @Test
    void givenRowWithEntry_WhenGetEntry_Index1_ExpectedNull() {
        addEntryToRow(entry, true);
        assertNull(row.getEntry(1));
    }

    @Test
    void givenRowWithEntry_WhenGetEntry_Index0_ExpectedMatches() {
        addEntryToRow(entry, true);
        assertEquals(entry, row.getEntry(0));
    }

    @Test
    void givenRowWithTwoEntries_WhenGetEntry_Index1_ExpectedMatches() throws DataSheetEntryException {
        DataSheetEntry<String> secondEntry = createDataSheetEntry();
        addEntryToRow(entry, true);
        addEntryToRow(secondEntry, true);
        assertEquals(secondEntry, row.getEntry(1));
    }

    @SuppressWarnings("unchecked")
    @Test
    void givenEmptyRow_WhenAddToGetEntries_ExpectedNotAddedToRow() {
        assertRowContainsElements();
        assertTrue(((DataSheetRow<String>)row).getEntries().add(entry));
        assertRowContainsElements();
    }


    @Test
    void givenEmptyRow_WhenToString_ExpectedToStringMatches() {
        assertToStringMatches("");
    }

    @Test
    void givenRowWithEntries_WhenToString_ExpectedMatches() {
        addEntryToRow(entry, true);
        assertToStringMatches("\n" + entry.toString());
    }


    /**
     * Asserts whether {@link #entry#toString()} matches a string built of given parameters
     *
     * @param entries Expected entries of {@link DataSheetRow}
     */
    private void assertToStringMatches(String entries) {
        assertEquals(String.format("%s: {\n\"type\": %s,\n\"entries\": [%s]\n}", DATASHEET_ROW_CLASS_NAME, DATASHEET_ROW_GENERIC_TYPE, entries), entry.toString());
    }

}
