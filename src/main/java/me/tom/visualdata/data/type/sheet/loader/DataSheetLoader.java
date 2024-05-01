package me.tom.visualdata.data.type.sheet.loader;

import me.tom.visualdata.data.type.sheet.DataSheet;

public interface DataSheetLoader {

    /**
     *  Loads a new {@link DataSheet}
     *
     * @return Loaded {@link DataSheet}
     */
    DataSheet load();

}
