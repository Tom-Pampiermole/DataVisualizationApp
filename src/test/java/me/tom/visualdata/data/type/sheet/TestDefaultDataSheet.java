package me.tom.visualdata.data.type.sheet;

import me.tom.visualdata.data.type.sheet.entry.DataSheetEntryException;
import me.tom.visualdata.data.type.sheet.entry.DefaultDataSheetEntry;
import me.tom.visualdata.data.type.sheet.column.DataSheetColumn;
import me.tom.visualdata.data.type.sheet.column.DataSheetColumnException;
import me.tom.visualdata.data.type.sheet.column.DefaultDataSheetColumn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDefaultDataSheet {
    private static final String DEFAULT_COLUMN_NAME = "Foo";
    private static final String UNUSED_COLUMN_NAME = "Bar";

    private static final String DEFAULT_ENTRY_VALUE = "Foo Bar";


    private DefaultDataSheet dataSheet;

    @BeforeEach
    void setupBeforeEach() {
        dataSheet = new DefaultDataSheet();
    }

    @Test
    void given_WhenAddColumn_NameNull_ExpectedFalse() {
        assertTrue(dataSheet.addColumn(null));
    }

    @Test
    void given_WhenAddColumn_NameEmpty_ExpectedFalse() {
        assertTrue(dataSheet.addColumn(""));
    }

    @Test
    void given_WhenAddColumn_NameDefault_ExpectedTrue() {
        addDefaultColumnToSheet();
    }

    /**
     *  Adds a new column to {@link #dataSheet} with {@link #DEFAULT_COLUMN_NAME} as name
     */
    private void addDefaultColumnToSheet() {
        assertTrue(dataSheet.addColumn(DEFAULT_COLUMN_NAME));
    }

    @Test
    void givenEmptyDataSheet_WhenGetColumnNames_ExpectedEmpty() {
        assertTrue(dataSheet.getColumnNames().isEmpty());
    }

    @Test
    void givenDataSheetWithColumn_WhenGetNames_ExpectedMatches() {
        addDefaultColumnToSheet();
        assertEquals(1, dataSheet.getColumnNames().size());
        assertEquals(new HashSet<>() {{ add(DEFAULT_COLUMN_NAME); }}, dataSheet.getColumnNames());
    }

    @Test
    void givenEmptyDataSheet_WhenGetColumnOfIndex_Index0_ExpectedNull() {
        assertNull(dataSheet.getColumnOfIndex(0));
    }

    @Test
    void givenEmptyDataSheet_WhenGetColumnOfIndex_IndexMinus1_ExpectedNull() {
        assertNull(dataSheet.getColumnOfIndex(-1));
    }

    @Test
    void givenDataSheetWithColumn_WhenGetColumnOfIndex_Index0_ExpectedNotNull_NameMatches() {
        addDefaultColumnToSheet();
        assertNotNull(dataSheet.getColumnOfIndex(0));
        assertEquals(DEFAULT_COLUMN_NAME, dataSheet.getColumnOfIndex(0).getName());
    }

    @SuppressWarnings("unchecked")
    @Test
    void givenColumnFromDataSheetByIndex_WhenAddEntry_ExpectedAddedToColumnInDataSheet() throws DataSheetEntryException {
        addDefaultColumnToSheet();
        DataSheetColumn<String> column = (DataSheetColumn<String>) dataSheet.getColumnOfIndex(0);
        assertTrue(column.getEntries().isEmpty());
        assertTrue(column.addEntry(new DefaultDataSheetEntry<>(String.class, DEFAULT_ENTRY_VALUE)));

        assertEquals(1, dataSheet.getColumnOfIndex(0).getEntries().size());
        assertEquals(DEFAULT_ENTRY_VALUE, dataSheet.getColumnOfIndex(0).getEntries().getFirst().getValue());
    }


    @Test
    void givenEmptyDataSheet_WhenGetColumn_NameDefault_ExpectedNull() {
        assertNull(dataSheet.getColumn(DEFAULT_COLUMN_NAME));
    }

    @Test
    void givenDataSheetWithColumn_WhenGetColumn_NameNull_ExpectedNull() {
        addDefaultColumnToSheet();
        assertNull(dataSheet.getColumn(null));
    }

    @Test
    void givenDataSheetWithColumn_WhenGetColumn_NameEmpty_ExpectedNull() {
        addDefaultColumnToSheet();
        assertNull(dataSheet.getColumn(""));
    }

    @Test
    void givenDataSheetWithColumnNamedDefault_WhenGetColumn_NameNotInUse_ExpectedNull() {
        addDefaultColumnToSheet();
        assertNull(dataSheet.getColumn(UNUSED_COLUMN_NAME));
    }

    @Test
    void givenDataSheetWithColumnNamedDefault_WhenGetColumn_NameDefault_ExpectedNotNull_NameMatches() {
        addDefaultColumnToSheet();
        assertNotNull(dataSheet.getColumn(DEFAULT_COLUMN_NAME));
        assertEquals(DEFAULT_COLUMN_NAME, dataSheet.getColumn(DEFAULT_COLUMN_NAME).getName());
    }

    @SuppressWarnings("unchecked")
    @Test
    void givenColumnFromDataSheet_WhenAddEntry_ExpectedAddedToColumnInDataSheet() throws DataSheetEntryException {
        addDefaultColumnToSheet();
        DataSheetColumn<String> column = (DataSheetColumn<String>) dataSheet.getColumn(DEFAULT_COLUMN_NAME);
        assertTrue(column.getEntries().isEmpty());
        assertTrue(column.addEntry(new DefaultDataSheetEntry<>(String.class, DEFAULT_ENTRY_VALUE)));

        assertEquals(1, dataSheet.getColumn(DEFAULT_COLUMN_NAME).getEntries().size());
        assertEquals(DEFAULT_ENTRY_VALUE, dataSheet.getColumn(DEFAULT_COLUMN_NAME).getEntries().getFirst().getValue());
    }

    @Test
    void givenEmptyDataSheet_WhenGetContents_ExpectedEmpty() {
        assertEquals(new ArrayList<>(), dataSheet.getContents());
    }

    @Test
    void givenDataSheetWithColumnNamedDefault_WhenGetContents_ExpectedSize1_ColumnNameMatches() {
        addDefaultColumnToSheet();
        assertEquals(1, dataSheet.getContents().size());
        assertEquals(DEFAULT_COLUMN_NAME, dataSheet.getColumnOfIndex(0).getName());
    }

    @Test
    void givenContentsOfDataSheet_WhenAdd_ExpectedNotAddedToDataSheet() throws DataSheetColumnException {
        List<DataSheetColumn<?>> contents = dataSheet.getContents();
        assertTrue(contents.isEmpty());

        assertTrue(contents.add(new DefaultDataSheetColumn<>(String.class, DEFAULT_COLUMN_NAME)));

        assertTrue(dataSheet.getContents().isEmpty());
    }

}
