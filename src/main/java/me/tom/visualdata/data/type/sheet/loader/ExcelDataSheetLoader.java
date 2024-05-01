package me.tom.visualdata.data.type.sheet.loader;

import me.tom.visualdata.data.type.sheet.DataSheet;
import me.tom.visualdata.data.type.sheet.DefaultDataSheet;

public class ExcelDataSheetLoader implements DataSheetLoader {
    private final String path;
    public ExcelDataSheetLoader(String path) throws DataSheetLoaderException {
        if(path == null || path.trim().isEmpty()) {
            throw new DataSheetLoaderException("Cannot instantiate: path null");
        }

        this.path = path;
    }


    @Override
    public DataSheet load() {
        return new DefaultDataSheet();
    }
}
