package edu.utn.gestion.service.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.utn.gestion.exception.FileGenerationException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Customer;
import edu.utn.gestion.model.Sale;
import com.itextpdf.text.Document;
import edu.utn.gestion.model.SaleDetail;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * Created by martin on 08/12/15.
 */
public class InvoiceFactory {
    private static final Logger LOGGER = Logger.getLogger(InvoiceFactory.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final String FILE_FORMAT = ".pdf";
    private static final String DEFAULT_INVOICE_TYPE = "FACTURA B";
    private static final String NEW_LINE = "\n";
    private static final String TABULAR = "\t";
    private static final String SEPARATOR = "\n-----------------------------------------------------" +
            "---------------------------------------------------------------------\n\n";

    /**
     * Generates an invoice in PDF for a given sale. After that, shows the invoice to the user.
     *
     * @param sale
     * @throws FileGenerationException If sale is null or if something goes wrong during the invoice generation.
     */
    public static void generateInvoice(Sale sale) throws FileGenerationException {
        if (sale == null) {
            throw new FileGenerationException("Cannot generate invoice for a null sale.");
        }

        String fileName = null;

        try {
            fileName = generatePDF(sale);
        } catch (DocumentException | FileNotFoundException ex) {
            String errorMessage = "Error during invoice generation for sale " + sale.getId();
            LOGGER.error(errorMessage, ex);
            throw new FileGenerationException(errorMessage, ex);
        }

        if (StringUtils.isNotEmpty(fileName)) {
            openInvoice(fileName);
        }
    }

    /**
     * Creates the PDF file for a given sale.
     *
     * @param sale
     * @return
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    protected static String generatePDF(Sale sale) throws DocumentException, FileNotFoundException {
        long saleId = sale.getId();

        LOGGER.info("Generating invoice for sale number: " + saleId);

        String fileName = getFileName(saleId);
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));

        writer.setCompressionLevel(0);

        document.open();

        Paragraph paragraph = new Paragraph("FACTURA");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        StringBuilder stringBuilder = new StringBuilder();
        paragraph = new Paragraph();
        paragraph.add(stringBuilder
                .append("GRUPO ILHSA S.A.")
                .append(NEW_LINE)
                .append("C.U.I.T: 30654386192")
                .append(NEW_LINE)
                .append("Ing. Brutos: 901 174423-2")
                .append(NEW_LINE)
                .append("25 de Mayo 182 - Provincia de Tucumán")
                .append(NEW_LINE)
                .append("Tel. 0381-431-0560/1")
                .append(NEW_LINE)
                .append("Tel. 0381-431-0560/1")
                .append(NEW_LINE)
                .append("Inicio de Actividades: 02/10/00")
                .append(NEW_LINE)
                .append("IVA RESPONSABLE INSCRIPTO")
                .append(NEW_LINE)
                .append(SEPARATOR)
                .toString());
        document.add(paragraph);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        paragraph.add("ORIGINAL\n");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        paragraph.add(stringBuilder
                .append("TIQUE ")
                .append(DEFAULT_INVOICE_TYPE)
                .append(" Nº 0001-000000")
                .append(String.valueOf(saleId))
                .append(NEW_LINE)
                .append("Fecha: ")
                .append(DATE_FORMAT.format(sale.getDate()))
                .append(NEW_LINE)
                .append(SEPARATOR)
                .toString());
        document.add(paragraph);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        Customer customer = sale.getCustomer();
        paragraph.add(stringBuilder
                .append(customer.getName())
                .append(NEW_LINE)
                .append("C.U.I.T.: ")
                .append(customer.getCuit())
                .append(NEW_LINE)
                .append(SEPARATOR)
                .toString());
        document.add(paragraph);

        PdfPTable table = new PdfPTable(5);
        table.addCell("ISBN");
        table.addCell("DESCRIPCION");
        table.addCell("PRECIO UNIT");
        table.addCell("CANTIDAD");
        table.addCell("IMPORTE");

        Book book = null;
        for(SaleDetail detail: sale.getSaleDetails()) {
            book = detail.getBook();
            table.addCell(book.getIsbn());
            table.addCell(book.getTitle());
            table.addCell(String.valueOf(book.getPrice()));
            table.addCell(String.valueOf(detail.getQuantity()));
            table.addCell(String.valueOf(detail.getAmount()));
        }
        document.add(table);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        paragraph.add(stringBuilder
                .append(NEW_LINE)
                .append("TOTAL ")
                .append(TABULAR)
                .append(" $ ")
                .append(TABULAR)
                .append(sale.getTotalAmount())
                .append(NEW_LINE)
                .toString());
        document.add(paragraph);

        document.close();

        LOGGER.info("Invoice generated successfully for sale " + saleId);
        LOGGER.info("File Name: " + fileName);

        return fileName;
    }

    /**
     * Opens a given PDF file with evince.
     *
     * @param fileName
     */
    protected static void openInvoice(String fileName) {
        try {
            Runtime.getRuntime().exec("evince " + fileName);
        } catch (Exception ex) {
            LOGGER.error("Error trying to open the invoice with evince.", ex);
        }
    }

    /**
     * Returns the file name for an invoice.
     *
     * @param saleId
     * @return
     */
    protected static String getFileName(long saleId) {
        return new StringBuilder()
                .append("fac0001-000000")
                .append(saleId)
                .append(FILE_FORMAT)
                .toString();
    }
}
