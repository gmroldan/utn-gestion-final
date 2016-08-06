package edu.utn.gestion.service.util.liquidacion;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.utn.gestion.exception.FileGenerationException;
import edu.utn.gestion.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
public class InvoiceFactoryRecibo {

    private static final Logger LOGGER = Logger.getLogger(InvoiceFactoryRecibo.class);
    private static final String FILE_FORMAT = ".pdf";
    private static final String NEW_LINE = "\n";
    private static final String SEPARATOR = "\n-----------------------------------------------------" +
            "---------------------------------------------------------------------\n\n";
    private static final String TABULAR = "\t";

    private static final int CONCEPTO_COL_SPAN = 5;
    public static final int CANT_COL_SPAN = 1;
    public static final int HABERES_COL_SPAN = 2;
    public static final int DESCUENTO_COL_SPAN = 2;
    public static final int DETAIL_TABLE_WIDTH = CONCEPTO_COL_SPAN + CANT_COL_SPAN + HABERES_COL_SPAN + DESCUENTO_COL_SPAN;

    private static Font tittle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC);
    private static Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    private static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

    public static void generarRecibo(Settlement settlement) throws FileGenerationException {
        if (settlement == null) {
            throw new FileGenerationException("Cannot generate invoice for a null settlement.");
        }

        String fileName = null;

        try {
            fileName = generatePDF(settlement);
        } catch (DocumentException | FileNotFoundException ex) {
            String errorMessage = "Error during invoice generation for sale " + settlement.getEmployee().getName() + " " + settlement.getPeriod();
            LOGGER.error(errorMessage, ex);
            throw new FileGenerationException(errorMessage, ex);
        }

        if (StringUtils.isNotEmpty(fileName)) {
            try {
                guardarRecibo(fileName, settlement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void guardarRecibo(String fileName, Settlement settlement) throws SQLException {

        try {

            Path path = Paths.get("C:\\Users\\ASUS\\Documents\\Proyectos_IntelliJ\\utn-gestion-final\\recibos\\" + settlement.getPeriod() + "\\" + fileName);
            byte[] byteArray = Files.readAllBytes(path);
            Blob blob = new javax.sql.rowset.serial.SerialBlob(byteArray);
            settlement.setRecibo(blob);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static Blob convertImageToBlob(BufferedImage bufferedImage) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Blob blob = null;
        byte[] bytes  = stream.toByteArray();
        try {
            blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blob;
    }

    protected static String generatePDF(Settlement settlement) throws DocumentException, FileNotFoundException {
        String period = settlement.getPeriod();
        String settlementId = settlement.getEmployee().getName().toString() + "_" + period.toString();

        LOGGER.info("Generating invoice for settlement: " + settlementId);

        File recibosFolder = new File("recibos");
        if (!recibosFolder.exists()) {
            if (recibosFolder.mkdir()) {
                System.out.println("Directory recibos is created!");
            } else {
                System.out.println("Directory already exist!");
            }
        }

        File periodFolder = new File("recibos\\" + period);
        if (!periodFolder.exists()) {
            if (periodFolder.mkdir()) {
                System.out.println("Period " + period + " is created!");
            } else {
                System.out.println("Period " + period + " already exist!");
            }
        }

        String fileName = settlementId + FILE_FORMAT;
        createDocument(settlement, period, fileName);

        LOGGER.info("Invoice generated successfully for settlement " + settlementId);
        LOGGER.info("File Name: " + fileName);

        return fileName;
    }

    private static void createDocument(Settlement settlement, String period, String fileName) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("recibos/" + period + "/" + fileName));

        writer.setCompressionLevel(0);

        document.open();

        //Breves datos de la empresa
        StringBuilder stringBuilder = new StringBuilder();
        Paragraph paragraph = new Paragraph();
        Phrase empresa = new Phrase("GRUPO ILHSA S.A.", tittle );
        Phrase cuit = new Phrase("C.U.I.T: 30654386192", normalFont );
        Phrase direccion = new Phrase("25 de Mayo 182 - Provincia de Tucumán", normalFont );
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
        Date date = new Date(ingress.getYear()-1900,ingress.getMonth()-1,ingress.getDate());
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

        createHeaderLine("Apellido y nombre", "Categoría", "Fecha de ingreso", "C.U.I.L.",
                        employee.getName(), settlement.getCategory(), formatIngress, employee.getCuit(), employeeTable);

        createHeaderLine("Categoría profesional", "Sindicato", "Antigüedad", "Sueldo Básico",
                        "Empleados de Comercio", "S.E.O.C.", antiguedad, String.valueOf(settlement.getSueldoBasico()), employeeTable);

        createHeaderLine("Periodo", "Cuenta", "Banco", "Importe",
                        settlement.getPeriod(), "00013621635849", "HSBC Bank", String.valueOf(settlement.getNetPay()), employeeTable);

        document.add(employeeTable);

        document.add(new Paragraph(NEW_LINE));

        //Cuerpo: Datos de la liquidación
        PdfPTable detailTable = new PdfPTable(DETAIL_TABLE_WIDTH);
        detailTable.setWidthPercentage(100);

        createDetailHeader(detailTable);

        createDetailBody(settlement, detailTable);

        createDetailFooter(String.valueOf(settlement.getRemunerationAmount()),
                String.valueOf(settlement.getDiscount()),
                String.valueOf(settlement.getNetPay()), detailTable);

        document.add(detailTable);

        Phrase totalPhrase = new Phrase("Neto Percibido: " + settlement.getNetPay(), boldFont);
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

    private static void createDetailBody(Settlement settlement, PdfPTable detailTable) {
        createOrderLine("Sueldo Básico"             ,"", String.valueOf(settlement.getSueldoBasico())       ,"", detailTable);
        createOrderLine("Presentismo"               ,"8.3%", String.valueOf(settlement.getPresenteeismAmount()) ,"", detailTable);
        createOrderLine("Adicional por antigüedad"  ,"2%", String.valueOf(settlement.getMontoPorAntiguedad()) ,"", detailTable);

        createOrderLine("Aporte para jubilación", "11%", "", String.valueOf(settlement.getRetireAmount())  , detailTable);
        createOrderLine("Aporte ley 19.032"     , "3%", "", String.valueOf(settlement.getLaw19032())      , detailTable);
        createOrderLine("Aporte Obra Social"    , "3%", "", String.valueOf(settlement.getSocialCare())    , detailTable);

        //unas cuantas lineas en blanco
        createOrderLine(" ", " ", " ", " ", detailTable);
        createOrderLine(" ", " ", " ", " ", detailTable);
        createOrderLine(" ", " ", " ", " ", detailTable);
    }

    private static void createDetailHeader(PdfPTable detailTable) {
        createDetailHeaderColumn(detailTable, "Concepto", CONCEPTO_COL_SPAN);
        createDetailHeaderColumn(detailTable, "Cant.", CANT_COL_SPAN);
        createDetailHeaderColumn(detailTable, "Haberes", HABERES_COL_SPAN);
        createDetailHeaderColumn(detailTable, "Descuentos", DESCUENTO_COL_SPAN);
    }

    private static void createDetailHeaderColumn(PdfPTable detailTable, String titulo, int span) {
        Phrase phrase = new Phrase(titulo, boldFont);
        PdfPCell cell = new PdfPCell(phrase);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(span);
        detailTable.addCell(cell);
    }

    private static void createDetailFooter(String s1, String s2, String s3, PdfPTable detailTable) {

        createDetailHeaderColumn(detailTable, "Totales" , CONCEPTO_COL_SPAN + CANT_COL_SPAN);
        createDetailHeaderColumn(detailTable, s1        , HABERES_COL_SPAN);
        createDetailHeaderColumn(detailTable, s2        , DESCUENTO_COL_SPAN);

    }

    private static void createOrderLine(String s, String s1, String s2, String s3, PdfPTable detailTable) {

        detailTable.addCell(createDetailBodyColumn(s,CONCEPTO_COL_SPAN));
        detailTable.addCell(createDetailBodyColumn(s1,CANT_COL_SPAN));
        detailTable.addCell(createDetailBodyColumn(s2,HABERES_COL_SPAN));
        detailTable.addCell(createDetailBodyColumn(s3,DESCUENTO_COL_SPAN));

    }

    private static PdfPCell createDetailBodyColumn(String string, int span) {
        Phrase phrase = new Phrase(string, normalFont);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setColspan(span);

        return cell;
    }

    private static void createHeaderLine(String titulo1, String titulo2, String titulo3, String titulo4,
                                              String valor1, String valor2, String valor3, String valor4, PdfPTable table) {

        createCenterCell(titulo1, table, boldFont);
        createCenterCell(titulo2, table, boldFont);
        createCenterCell(titulo3, table, boldFont);
        createCenterCell(titulo4, table, boldFont);

        createCenterCell(valor1, table, normalFont);
        createCenterCell(valor2, table, normalFont);
        createCenterCell(valor3, table, normalFont);
        createCenterCell(valor4, table, normalFont);

    }

    private static void createCenterCell(String titulo1, PdfPTable table, Font boldFont) {
        Phrase phrase = new Phrase(titulo1, boldFont);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

}
