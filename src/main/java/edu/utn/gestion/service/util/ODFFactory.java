package edu.utn.gestion.service.util;

import edu.utn.gestion.exception.FileGenerationException;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;
import org.apache.commons.lang3.StringUtils;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by martin on 09/12/15.
 */
public class ODFFactory {
    private static final String FILE_EXTENSION = ".ods";
    private static final String EMPTY_SPACE = " ";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static void doExport(String fileName, GenericTableModel model) throws FileGenerationException {
        if (model == null || StringUtils.isEmpty(fileName)) {
            throw new FileGenerationException("Cannot generate an ODS file for a null object.");
        }

        File file = new File(getFormattedFileName(fileName));
        try {
            SpreadSheet.createEmpty(model).saveAs(file);
            OOUtils.open(file);
        } catch (IOException ex) {
            throw new FileGenerationException(ex);
        }
    }

    protected static String getFormattedFileName(String fileName) {
        return new StringBuilder()
                .append(fileName)
                .append(EMPTY_SPACE)
                .append(DATE_FORMAT.format(new Date()))
                .append(FILE_EXTENSION).toString();
    }
}
