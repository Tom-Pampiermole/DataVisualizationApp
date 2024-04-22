package me.tom.visualdata.data.type.sheet.entry;

import me.tom.visualdata.data.type.sheet.DataSheetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDataSheetEntry {
    private static final String ENTRY_CLASS_NAME = DefaultDataSheetEntry.class.getSimpleName();
    private static final String ENTRY_VALUE = "Foo";
    private static final Class<?> ENTRY_GENERIC_TYPE = String.class;


    private DataSheetEntry<?> entry;

    @BeforeEach
    void setupBeforeEach() {
        entry = null;
    }

    @Test
    void given_WhenInstantiate_GenericTypeNull_ExpectedException() {
        assertThrows(DataSheetException.class, () -> new DefaultDataSheetEntry<>(null, ENTRY_VALUE));
    }

    @Test
    void given_WhenInstantiate_EntryNull_ExpectedToStringMatches() throws DataSheetException {
        entry = new DefaultDataSheetEntry<>(ENTRY_GENERIC_TYPE, null);
        assertToStringMatches(ENTRY_GENERIC_TYPE, null);
    }

    @Test
    void given_WhenInstantiate_EntryTypeObject_ExpectedToStringMatches() throws DataSheetException {
        entry = new DefaultDataSheetEntry<>(Object.class, ENTRY_VALUE);
        assertToStringMatches(Object.class, ENTRY_VALUE);
    }

    @Test
    void givenEntryWithValueFoo_WhenGetValue_ExpectedMatches() throws DataSheetException {
        entry = new DefaultDataSheetEntry<>(String.class, ENTRY_VALUE);
        assertEquals(ENTRY_VALUE, entry.getValue());
    }

    @Test
    void givenEntryWithTypeString_WhenGetType_ExpectedMatches() throws DataSheetException {
        entry = new DefaultDataSheetEntry<>(String.class, ENTRY_VALUE);
        assertEquals(ENTRY_GENERIC_TYPE, entry.getType());
    }

    @Test
    void givenEntryWithTypeObject_WhenGetType_ExpectedMatches() throws DataSheetException {
        entry = new DefaultDataSheetEntry<>(Object.class, ENTRY_VALUE);
        assertEquals(Object.class, entry.getType());
    }



    /**
     * Asserts whether {@link #entry#toString()} matches a string built of given parameters
     *
     * @param genericType Expected generic type of {@link #entry}
     * @param value Expected value of {@link #entry}
     */
    private void assertToStringMatches(Class<?> genericType, Object value) {
        assertEquals(String.format("%s: {\n\"type\": %s,\n\"value\": %s\n}", ENTRY_CLASS_NAME, value, genericType), entry.toString());
    }

}
