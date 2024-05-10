package me.tom.visualdata.data.type.table;

import me.tom.visualdata.scene.components.data.DataTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class SheetDataTableLoader {
    private static final int WORKSHEET_INDEX = 0;


    private final String path;
    public SheetDataTableLoader(String path) {
        if(path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Cannot instantiate: path null");
        }

        this.path = path;
    }


    public void load(DataTable dataTable) {
        File file = new File(path);
        if(!file.exists()) {
            return;
        }

        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            load(dataTable, fileInputStream);
        } catch (IOException ignored) {

        }
    }

    /**
     *  Loads given {@link DataTable} using given {@link FileInputStream}
     *
     * @param dataTable {@link DataTable} to load data into
     * @param fileInputStream {@link FileInputStream} to be used to load data from
     * @throws IOException Thrown when {@link XSSFWorkbook} could not be created
     */
    private void load(DataTable dataTable, FileInputStream fileInputStream) throws IOException {
        assert fileInputStream != null : "Cannot load, fileInputStream null";
        Workbook workbook = new XSSFWorkbook(fileInputStream); // TODO Make a provider instead of separate loader due to this being the only difference
        Sheet sheet = workbook.getSheetAt(WORKSHEET_INDEX);

        Iterator<Row> rowIterator = sheet.rowIterator();
        addColumnsFromRowToTableViewAsColumn(dataTable, rowIterator);
    }

    /**
     *  Adds the next {@link Row} from given {@link Iterator} as new columns to given {@link DataTable}
     *
     * @param dataTable {@link DataTable} to add column to
     * @param rowIterator {@link Iterator} of each {@link Row} to be used to add to given {@link DataTable}
     */
    private void addColumnsFromRowToTableViewAsColumn(DataTable dataTable, Iterator<Row> rowIterator) {
        if(!rowIterator.hasNext()) {
            return;
        }

        Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
        while(cellIterator.hasNext()) {
            dataTable.addColumn(cellIterator.next().toString());
        }
    }
}
