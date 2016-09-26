package edu.utn.gestion.service.util;

import com.itextpdf.text.DocumentException;
import edu.utn.gestion.exception.FileGenerationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;

/**
 * Created by martin on 11/08/16.
 */
public abstract class AbstractPDFFactory<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractPDFFactory.class);
    protected static final String FILE_FORMAT = ".pdf";
    protected static final String NEW_LINE = "\n";
    protected static final String TABULAR = "\t";
    protected static final String SEPARATOR = "\n-----------------------------------------------------" +
            "---------------------------------------------------------------------\n\n";

    public  void generate(final T object) throws FileGenerationException {
        if (object == null) {
            throw new FileGenerationException("Cannot generate PDF for a null object.");
        }

        String fileName = null;

        try {
            fileName = this.generatePDF(object);
        } catch (DocumentException | FileNotFoundException ex) {
            String errorMessage = "Error during PDF generation for " + object;
            LOGGER.error(errorMessage, ex);
            throw new FileGenerationException(errorMessage, ex);
        }

        if (StringUtils.isNotEmpty(fileName)) {
            this.postGeneration(fileName);
        }
    }

    /**
     * Opens a given PDF file with evince.
     *
     * @param fileName
     */
    public void openInvoice(final String fileName) {
        LOGGER.info(String.format("Opening %s with evince", fileName));

        try {
            if (SystemUtils.IS_OS_LINUX) {
                Runtime.getRuntime().exec("evince " + fileName);
            } else {
                throw new UnsupportedOperationException("This feature is only available for linux.");
            }
        } catch (Exception ex) {
            LOGGER.error("Error trying to open the invoice with evince.", ex);
        }
    }

    protected abstract String generatePDF(T object) throws DocumentException, FileNotFoundException;

    protected abstract void postGeneration(String fileName);
}
