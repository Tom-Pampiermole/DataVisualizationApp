package me.tom.visualdata.data.type.sheet.loader;

import me.tom.visualdata.data.type.sheet.DataSheet;
import me.tom.visualdata.data.type.sheet.entry.DataSheetEntry;
import me.tom.visualdata.data.type.sheet.column.DataSheetColumn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestExcelDataSheetLoader {
    private static final String DATASHEET_PATH = "datasheet/ExcelDataSheet.xlsx";
    private static final String DATASHEET_PATH_NON_EXISTENT = "datasheet/non-existent/ExcelDataSheet.xlsx";
    private static final List<String> DATASHEET_HEADERS = new ArrayList<>() {{ add("first_name"); add("last_name"); add("age"); }};
    private static final List<String> DATASHEET_ROW_1 = new ArrayList<>() {{ add("John"); add("Doe"); add("32"); }};


    private DataSheetLoader dataSheetLoader;

    @BeforeEach
    void setupBeforeEach() throws DataSheetLoaderException {
        dataSheetLoader = new ExcelDataSheetLoader(DATASHEET_PATH);
    }

    @Test
    void given_WhenInstantiate_PathNull_ExpectedException() {
        assertThrows(DataSheetLoaderException.class, () -> new ExcelDataSheetLoader(null));
    }

    @Test
    void given_WhenInstantiate_PathEmpty_ExpectedException() {
        assertThrows(DataSheetLoaderException.class, () -> new ExcelDataSheetLoader(""));
    }

    @Test
    void given_WhenInstantiate_PathNotEmpty_ExpectedNoException() {
        try {
            new ExcelDataSheetLoader(DATASHEET_PATH);
        } catch(Exception e) {
            fail(e);
        }
    }

    @Test
    void givenNonExistentSheet_WhenLoad_ExpectedSheetEmpty() throws DataSheetLoaderException {
        assertNull(getClass().getResource(DATASHEET_PATH_NON_EXISTENT));
        assertTrue(new ExcelDataSheetLoader(DATASHEET_PATH_NON_EXISTENT).load().getContents().isEmpty());
    }

    @Test
    void givenSheetWithOnlyHeaders_WhenLoad_ExpectedRowNamesMatch_RowsEmpty() {
        assertNotNull(getClass().getResource(DATASHEET_PATH));
        assertHeaderRowMatches(dataSheetLoader.load());
    }

    /**
     *  Asserts the first row of given {@link DataSheet} (header row) has headers matching {@link #DATASHEET_HEADERS}
     *
     * @param dataSheet {@link DataSheet} to have its' header row asserted
     */
    private void assertHeaderRowMatches(DataSheet dataSheet) {
        assertNotNull(dataSheet.getColumnOfIndex(0));
        assertRowMatches(dataSheet.getColumnOfIndex(0), DATASHEET_HEADERS);
    }

    /**
     *  Asserts given {@link DataSheetColumn} has values matching expected values
     *
     * @param row {@link DataSheetColumn} to have its' values asserted
     * @param expected Expected values from {@link DataSheetColumn}
     */
    private void assertRowMatches(DataSheetColumn<?> row, List<String> expected) {
        List<? extends DataSheetEntry<?>> entries = row.getEntries();
        for(int i = 0; i < entries.size(); i++) {
            assertEquals(expected.get(i), entries.get(i).getValue());
        }
    }

    @Test
    void givenSheetWithTwoRows_WhenLoad_ExpectedHeadersMatch_RowsMatch() {
        assertNotNull(getClass().getResource(DATASHEET_PATH));

        DataSheet dataSheet = dataSheetLoader.load();
        assertHeaderRowMatches(dataSheet);

        assertNotNull(dataSheet.getColumnOfIndex(1));
        assertNull(dataSheet.getColumnOfIndex(2));
        assertRowMatches(dataSheet.getColumnOfIndex(1), DATASHEET_ROW_1);
    }


}
