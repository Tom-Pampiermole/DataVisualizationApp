package me.tom.visualdata.scene.components.data;

import javafx.scene.control.TableView;
import me.tom.visualdata.data.type.sheet.DataSheet;
import me.tom.visualdata.data.type.sheet.DefaultDataSheet;
import me.tom.visualdata.scene.components.loader.ComponentLoaderException;
import me.tom.visualdata.scene.components.loader.FXMLComponentLoader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;

public class DataTable extends TableView<String> {
    private static final String COMPONENT_NAME = "data/DataTable";

    public DataTable() throws ComponentLoaderException {
        new FXMLComponentLoader().load(COMPONENT_NAME, this);

        URL urlOfFile = getClass().getClassLoader().getResource("dummydata.xlsx");
        if(urlOfFile == null) {
            return;
        }

        try(FileInputStream fileInputStream = new FileInputStream(new File(urlOfFile.toURI()))) {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheetAt(0);

            DataSheet dataSheet = new DefaultDataSheet();
            Iterator<Row> worksheetRowIterator = worksheet.rowIterator();
            if(!worksheetRowIterator.hasNext()) {
                return;
            }


            // Load headers
            Row headerRow = worksheetRowIterator.next();
            Iterator<Cell> headerRowCells = headerRow.cellIterator();
            while(headerRowCells.hasNext()) {
                Cell headerCell = headerRowCells.next();
                dataSheet.addRow(headerCell.toString());
                System.out.printf("%20s | ", headerCell.toString());
            }
            System.out.println();

            // Load cells
            while(worksheetRowIterator.hasNext()) {
                Row row = worksheetRowIterator.next();
                
                Iterator<Cell> cellIteratorOfRow = row.cellIterator();
                while(cellIteratorOfRow.hasNext()) {
                    Cell cell = cellIteratorOfRow.next();
                    System.out.printf("%20s | ", cell.toString());
                }

                System.out.println();
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }


}
