package me.tom.visualdata.data.type.sheet;

import me.tom.visualdata.data.type.sheet.entry.DataSheetEntryException;
import me.tom.visualdata.data.type.sheet.entry.DefaultDataSheetEntry;
import me.tom.visualdata.data.type.sheet.row.DataSheetRow;
import me.tom.visualdata.data.type.sheet.row.DataSheetRowException;
import me.tom.visualdata.data.type.sheet.row.DefaultDataSheetRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDefaultDataSheet {
    private static final String DEFAULT_ROW_NAME = "Foo";
    private static final String UNUSED_ROW_NAME = "Bar";

    private static final String DEFAULT_ENTRY_VALUE = "Foo Bar";


    private DefaultDataSheet dataSheet;

    @BeforeEach
    void setupBeforeEach() {
        dataSheet = new DefaultDataSheet();
    }

    @Test
    void given_WhenAddRow_NameNull_ExpectedFalse() {
        assertTrue(dataSheet.addRow(null));
    }

    @Test
    void given_WhenAddRow_NameEmpty_ExpectedFalse() {
        assertTrue(dataSheet.addRow(""));
    }

    @Test
    void given_WhenAddRow_NameDefault_ExpectedTrue() {
        addDefaultRowToSheet();
    }

    /**
     *  Adds a new row to {@link #dataSheet} with {@link #DEFAULT_ROW_NAME} as name
     */
    private void addDefaultRowToSheet() {
        assertTrue(dataSheet.addRow(DEFAULT_ROW_NAME));
    }

    @Test
    void givenEmptyDataSheet_WhenGetRowNames_ExpectedEmpty() {
        assertTrue(dataSheet.getRowNames().isEmpty());
    }

    @Test
    void givenDataSheetWithRow_WhenGetNames_ExpectedMatches() {
        addDefaultRowToSheet();
        assertEquals(1, dataSheet.getRowNames().size());
        assertEquals(new HashSet<>() {{ add(DEFAULT_ROW_NAME); }}, dataSheet.getRowNames());
    }

    @Test
    void givenEmptyDataSheet_WhenGetRowOfIndex_Index0_ExpectedNull() {
        assertNull(dataSheet.getRowOfIndex(0));
    }

    @Test
    void givenEmptyDataSheet_WhenGetRowOfIndex_IndexMinus1_ExpectedNull() {
        assertNull(dataSheet.getRowOfIndex(-1));
    }

    @Test
    void givenDataSheetWithRow_WhenGetRowOfIndex_Index0_ExpectedNotNull_NameMatches() {
        addDefaultRowToSheet();
        assertNotNull(dataSheet.getRowOfIndex(0));
        assertEquals(DEFAULT_ROW_NAME, dataSheet.getRowOfIndex(0).getName());
    }

    @SuppressWarnings("unchecked")
    @Test
    void givenRowFromDataSheetByIndex_WhenAddEntry_ExpectedAddedToRowInDataSheet() throws DataSheetEntryException {
        addDefaultRowToSheet();
        DataSheetRow<String> row = (DataSheetRow<String>) dataSheet.getRowOfIndex(0);
        assertTrue(row.getEntries().isEmpty());
        assertTrue(row.addEntry(new DefaultDataSheetEntry<>(String.class, DEFAULT_ENTRY_VALUE)));

        assertEquals(1, dataSheet.getRowOfIndex(0).getEntries().size());
        assertEquals(DEFAULT_ENTRY_VALUE, dataSheet.getRowOfIndex(0).getEntries().getFirst().getValue());
    }


    @Test
    void givenEmptyDataSheet_WhenGetRow_NameDefault_ExpectedNull() {
        assertNull(dataSheet.getRow(DEFAULT_ROW_NAME));
    }

    @Test
    void givenDataSheetWithRow_WhenGetRow_NameNull_ExpectedNull() {
        addDefaultRowToSheet();
        assertNull(dataSheet.getRow(null));
    }

    @Test
    void givenDataSheetWithRow_WhenGetRow_NameEmpty_ExpectedNull() {
        addDefaultRowToSheet();
        assertNull(dataSheet.getRow(""));
    }

    @Test
    void givenDataSheetWithRowNamedDefault_WhenGetRow_NameNotInUse_ExpectedNull() {
        addDefaultRowToSheet();
        assertNull(dataSheet.getRow(UNUSED_ROW_NAME));
    }

    @Test
    void givenDataSheetWithRowNamedDefault_WhenGetRow_NameDefault_ExpectedNotNull_NameMatches() {
        addDefaultRowToSheet();
        assertNotNull(dataSheet.getRow(DEFAULT_ROW_NAME));
        assertEquals(DEFAULT_ROW_NAME, dataSheet.getRow(DEFAULT_ROW_NAME).getName());
    }

    @SuppressWarnings("unchecked")
    @Test
    void givenRowFromDataSheet_WhenAddEntry_ExpectedAddedToRowInDataSheet() throws DataSheetEntryException {
        addDefaultRowToSheet();
        DataSheetRow<String> row = (DataSheetRow<String>) dataSheet.getRow(DEFAULT_ROW_NAME);
        assertTrue(row.getEntries().isEmpty());
        assertTrue(row.addEntry(new DefaultDataSheetEntry<>(String.class, DEFAULT_ENTRY_VALUE)));

        assertEquals(1, dataSheet.getRow(DEFAULT_ROW_NAME).getEntries().size());
        assertEquals(DEFAULT_ENTRY_VALUE, dataSheet.getRow(DEFAULT_ROW_NAME).getEntries().getFirst().getValue());
    }

    @Test
    void givenEmptyDataSheet_WhenGetContents_ExpectedEmpty() {
        assertEquals(new ArrayList<>(), dataSheet.getContents());
    }

    @Test
    void givenDataSheetWithRowNamedDefault_WhenGetContents_ExpectedSize1_RowNameMatches() {
        addDefaultRowToSheet();
        assertEquals(1, dataSheet.getContents().size());
        assertEquals(DEFAULT_ROW_NAME, dataSheet.getContents().getFirst().getName());
    }

    @Test
    void givenContentsOfDataSheet_WhenAdd_ExpectedNotAddedToDataSheet() throws DataSheetRowException {
        List<DataSheetRow<?>> contents = dataSheet.getContents();
        assertTrue(contents.isEmpty());

        assertTrue(contents.add(new DefaultDataSheetRow<>(String.class, DEFAULT_ROW_NAME)));

        assertTrue(dataSheet.getContents().isEmpty());
    }

}
