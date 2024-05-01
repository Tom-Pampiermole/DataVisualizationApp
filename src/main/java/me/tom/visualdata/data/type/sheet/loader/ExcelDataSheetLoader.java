package me.tom.visualdata.data.type.sheet.loader;

import me.tom.visualdata.data.type.sheet.DataSheet;
import me.tom.visualdata.data.type.sheet.DefaultDataSheet;

public class ExcelDataSheetLoader implements DataSheetLoader {
    public ExcelDataSheetLoader(String path) {

    }


    @Override
    public DataSheet load() {
        return new DefaultDataSheet();
    }
}
