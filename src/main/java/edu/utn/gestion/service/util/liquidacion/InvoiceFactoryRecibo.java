package edu.utn.gestion.service.util.liquidacion;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Settlement;
import edu.utn.gestion.service.util.AbstractPDFFactory;
import org.apache.log4j.Logger;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ASUS on 28/07/2016.
 */
public class InvoiceFactoryRecibo extends AbstractPDFFactory<Settlement> {
    private static final InvoiceFactoryRecibo INSTANCE = new InvoiceFactoryRecibo();
    private static final Logger LOGGER = Logger.getLogger(InvoiceFactoryRecibo.class);
    private static final String FOLDER_NAME = "recibos";
    private static final int CONCEPTO_COL_SPAN = 5;
    private static final Font TITTLE_FONT
            = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC);
    private static final Font BOLD_FONT
            = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    private static final Font NORMAL_FONT
            = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
    public static final int CANT_COL_SPAN = 1;
    public static final int HABERES_COL_SPAN = 2;
    public static final int DESCUENTO_COL_SPAN = 2;
    public static final int DETAIL_TABLE_WIDTH
            = CONCEPTO_COL_SPAN + CANT_COL_SPAN + HABERES_COL_SPAN + DESCUENTO_COL_SPAN;

    /**
     * Class constructor.
     */
    private InvoiceFactoryRecibo() {}

    /**
     * Returns the unique instance of InvoiceFactoryRecibo.
     *
     * @return
     */
    public static InvoiceFactoryRecibo getInstance() {
        return INSTANCE;
    }

    private static void guardarRecibo(String fileName, Settlement settlement) throws SQLException {
        try {
            Path path = Paths.get(new StringBuilder()
                    .append("recibos/")
                    .append(settlement.getPeriod())
                    .append("/")
                    .append(fileName)
                    .toString());
            byte[] byteArray = Files.readAllBytes(path);
            Blob blob = new SerialBlob(byteArray);
            settlement.setRecibo(blob);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String generatePDF(Settlement settlement) throws DocumentException, FileNotFoundException {
        String period = settlement.getPeriod();
        String settlementId = new StringBuilder()
                .append(settlement.getEmployee().getName())
                .append("_")
                .append(period.toString())
                .toString();

        LOGGER.info("Generating invoice for settlement: " + settlementId);

        File recibosFolder = new File(FOLDER_NAME);
        if (!recibosFolder.exists()) {
            if (recibosFolder.mkdir()) {
                LOGGER.info("Directory recibos has been created!");
            } else {
                LOGGER.info("Directory already exists!");
            }
        }

        File periodFolder = new File("recibos/" + period);
        if (!periodFolder.exists()) {
            if (periodFolder.mkdir()) {
                LOGGER.info("Period " + period + " has been created!");
            } else {
                LOGGER.info("Period " + period + " already exists!");
            }
        }

        String fileName = settlementId + FILE_FORMAT;
        this.createDocument(settlement, period, fileName);

        LOGGER.info("Invoice generated successfully for settlement " + settlementId);
        LOGGER.info("File Name: " + fileName);

        return fileName;
    }

    @Override
    protected void postGeneration(String fileName) {
        // TODO Add implementation.
        /*try {
            guardarRecibo(fileName, settlement);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    private void createDocument(Settlement settlement, String period, String fileName)
            throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter writer
                = PdfWriter.getInstance(document,
                                        new FileOutputStream("recibos/" + period + "/" + fileName));

        writer.setCompressionLevel(0);

        document.open();

        //Breves datos de la empresa
        StringBuilder stringBuilder = new StringBuilder();
        Paragraph paragraph = new Paragraph();
        Phrase empresa = new Phrase("GRUPO ILHSA S.A.", TITTLE_FONT);
        Phrase cuit = new Phrase("C.U.I.T: 30654386192", NORMAL_FONT);
        Phrase direccion = new Phrase("25 de Mayo 182 - Provincia de Tucumán", NORMAL_FONT);
        paragraph.add(empresa);
        paragraph.add(NEW_LINE);
        paragraph.add(cuit);
        paragraph.add(NEW_LINE);
        paragraph.add(direccion);
        paragraph.add(NEW_LINE);
        paragraph.add(NEW_LINE);
        document.add(paragraph);

        //Cabezal: datos del empleado
        Employee employee = settlement.getEmployee();
        Date ingress = employee.getIngress();
        Date date = new Date(ingress.getYear(),ingress.getMonth()-1,ingress.getDate());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formatIngress = formatter.format(date);
        String antiguedad;
        if (settlement.getAntiguedad() == 0) {
            antiguedad = "Ingresante";
        }
        if (settlement.getAntiguedad() == 1) {
            antiguedad = "1 mes";
        } else {
            antiguedad = String.valueOf(settlement.getAntiguedad()) + " meses";
        }

        PdfPTable employeeTable = new PdfPTable(4);
        employeeTable.setWidthPercentage(100);

        this.createHeaderLine("Apellido y nombre", "Categoría", "Fecha de ingreso", "C.U.I.L.",
                        employee.getName(), settlement.getCategory(), formatIngress, employee.getCuit(), employeeTable);

        this.createHeaderLine("Categoría profesional", "Sindicato", "Antigüedad", "Sueldo Básico",
                        "Empleados de Comercio", "S.E.O.C.", antiguedad, String.valueOf(settlement.getSueldoBasico()), employeeTable);

        this.createHeaderLine("Periodo", "Cuenta", "Banco", "Importe",
                        settlement.getPeriod(), "00013621635849", "HSBC Bank", String.valueOf(settlement.getNetPay()), employeeTable);

        document.add(employeeTable);

        document.add(new Paragraph(NEW_LINE));

        //Cuerpo: Datos de la liquidación
        PdfPTable detailTable = new PdfPTable(DETAIL_TABLE_WIDTH);
        detailTable.setWidthPercentage(100);

        this.createDetailHeader(detailTable);
        this.createDetailBody(settlement, detailTable);
        this.createDetailFooter(String.valueOf(settlement.getRemunerationAmount()),
                String.valueOf(settlement.getDiscount()),
                String.valueOf(settlement.getNetPay()), detailTable);

        document.add(detailTable);

        Phrase totalPhrase = new Phrase("Neto Percibido: " + settlement.getNetPay(), BOLD_FONT);
        Paragraph totalParagraph = new Paragraph();
        totalParagraph.setAlignment(Element.ALIGN_RIGHT);
        totalParagraph.add(totalPhrase);
        totalParagraph.add(NEW_LINE);
        document.add(totalParagraph);

        //espacio para firmar
        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        paragraph.add(stringBuilder
                .append(NEW_LINE)
                .append(NEW_LINE)
                .append(NEW_LINE)
                .append(NEW_LINE)
                .append("...................................")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("...................................")
                .append(NEW_LINE)
                .append("Firma del empleado  ")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("      ")
                .append("Firma del empleador ")
                .append(NEW_LINE)
                .toString());
        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);

        document.close();
    }

    private void createDetailBody(Settlement settlement, PdfPTable detailTable) {
        this.createOrderLine("Sueldo Básico"             ,"", String.valueOf(settlement.getSueldoBasico())       ,"", detailTable);
        this.createOrderLine("Presentismo"               ,"8.3%", String.valueOf(settlement.getPresenteeismAmount()) ,"", detailTable);
        this.createOrderLine("Adicional por antigüedad"  ,"2%", String.valueOf(settlement.getMontoPorAntiguedad()) ,"", detailTable);
        if (settlement.getAsignacionFamiliar() > 0) {
            this.createOrderLine("Asignacion familiar"  ," ", String.valueOf(settlement.getAsignacionFamiliar()) ,"", detailTable);
        }

        this.createOrderLine("Aporte para jubilación", "11%", "", String.valueOf(settlement.getRetireAmount())  , detailTable);
        this.createOrderLine("Aporte ley 19.032"     , "3%", "", String.valueOf(settlement.getLaw19032())      , detailTable);
        this.createOrderLine("Aporte Obra Social"    , "3%", "", String.valueOf(settlement.getSocialCare())    , detailTable);

        //unas cuantas lineas en blanco
        this.createOrderLine(" ", " ", " ", " ", detailTable);
        this.createOrderLine(" ", " ", " ", " ", detailTable);
        this.createOrderLine(" ", " ", " ", " ", detailTable);
    }

    private void createDetailHeader(PdfPTable detailTable) {
        this.createDetailHeaderColumn(detailTable, "Concepto", CONCEPTO_COL_SPAN);
        this.createDetailHeaderColumn(detailTable, "Cant.", CANT_COL_SPAN);
        this.createDetailHeaderColumn(detailTable, "Haberes", HABERES_COL_SPAN);
        this.createDetailHeaderColumn(detailTable, "Descuentos", DESCUENTO_COL_SPAN);
    }

    private void createDetailHeaderColumn(PdfPTable detailTable, String titulo, int span) {
        Phrase phrase = new Phrase(titulo, BOLD_FONT);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setColspan(span);
        detailTable.addCell(cell);
    }

    private void createDetailFooter(String s1, String s2, String s3, PdfPTable detailTable) {
        this.createDetailHeaderColumn(detailTable, "Totales" , CONCEPTO_COL_SPAN + CANT_COL_SPAN);
        this.createDetailHeaderColumn(detailTable, s1        , HABERES_COL_SPAN);
        this.createDetailHeaderColumn(detailTable, s2        , DESCUENTO_COL_SPAN);
    }

    private void createOrderLine(String s, String s1, String s2, String s3, PdfPTable detailTable) {
        detailTable.addCell(createDetailBodyColumn(s,CONCEPTO_COL_SPAN));
        detailTable.addCell(createDetailBodyColumn(s1,CANT_COL_SPAN));
        detailTable.addCell(createDetailBodyColumn(s2,HABERES_COL_SPAN));
        detailTable.addCell(createDetailBodyColumn(s3,DESCUENTO_COL_SPAN));
    }

    private PdfPCell createDetailBodyColumn(String string, int span) {
        Phrase phrase = new Phrase(string, NORMAL_FONT);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setColspan(span);
        return cell;
    }

    private void createHeaderLine(String titulo1, String titulo2, String titulo3, String titulo4,
                                              String valor1, String valor2, String valor3, String valor4, PdfPTable table) {
        this.createCenterCell(titulo1, table, BOLD_FONT);
        this.createCenterCell(titulo2, table, BOLD_FONT);
        this.createCenterCell(titulo3, table, BOLD_FONT);
        this.createCenterCell(titulo4, table, BOLD_FONT);

        this.createCenterCell(valor1, table, NORMAL_FONT);
        this.createCenterCell(valor2, table, NORMAL_FONT);
        this.createCenterCell(valor3, table, NORMAL_FONT);
        this.createCenterCell(valor4, table, NORMAL_FONT);
    }

    private void createCenterCell(String titulo1, PdfPTable table, Font boldFont) {
        Phrase phrase = new Phrase(titulo1, boldFont);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}
