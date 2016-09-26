package edu.utn.gestion.ui.dialog.settlement;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Settlement;
import edu.utn.gestion.service.util.InvoiceFactory;
import edu.utn.gestion.ui.MainFrame;
import edu.utn.gestion.ui.controller.SettlementController;
import edu.utn.gestion.ui.dialog.employee.ComboEmployees;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ASUS on 23/02/2016.
 */
public class SettlementHistoryDialog extends JDialog{

    private JLabel lblEmployee;

    private ComboEmployees cmbEmployees;
    private JButton btnVer;
    private JButton btnCancelar;

    private JTable table;
    private SettlementTableModel tableModel;

    private SettlementController controller;
    private JPanel panel;

    public SettlementHistoryDialog(MainFrame parent, boolean modal) {
        super(parent, modal);
        this.setSize(500,300);
        initComponents();
    }

    protected void initComponents() {

        controller = new SettlementController();

        lblEmployee = new JLabel("Employee");

        cmbEmployees = new ComboEmployees();
        cmbEmployees.addItemListener(e -> resetComponents());

        tableModel = new SettlementTableModel();
        table = new JTable(tableModel);
        this.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });

        btnVer = new JButton("Ver Recibo");
        btnVer.addActionListener(event -> this.btnVerActionPerformed());

        btnCancelar = new JButton("Volver");
        btnCancelar.addActionListener(event -> this.btnCancelActionPerformed());

        resetComponents();

        createFormPanel();
    }

    private void resetComponents() {

        List<Settlement> settlements;

        try {
            settlements = controller.findAll();
            tableModel.setObjectList(filterByEmployee(settlements, this.cmbEmployees.getEmployee()));

        } catch (GestionAppException e) {
            e.printStackTrace();
        }
    }

    /**
     * sory por no hacerlo en el back end XD, me dio p*ja
     *
     * @param settlements
     * @param employee
     * @return
     */
    private List<Settlement> filterByEmployee(List<Settlement> settlements, Employee employee) {

        List<Settlement> filterSettlementList = new ArrayList<>();
        for (Settlement settlement : settlements) {
            if (settlement.getEmployee().getCuit().equals(employee.getCuit())) {
                filterSettlementList.add(settlement);
            }
        }
        return filterSettlementList;
    }

    protected void createFormPanel() {

        this.panel = new JPanel(new BorderLayout());

        this.panel.add(this.cmbEmployees,BorderLayout.PAGE_START);

        JScrollPane scrollPane = new JScrollPane(this.table);
        scrollPane.setSize(400,200);
        this.panel.add(scrollPane, BorderLayout.CENTER);

        JPanel endPage = new JPanel(new FlowLayout());
        endPage.add(this.btnVer);
        endPage.add(this.btnCancelar);

        this.panel.add(endPage,BorderLayout.PAGE_END);

        this.setResizable(true);
        this.setContentPane(this.panel);
    }

    protected void btnVerActionPerformed() {

        Set<Settlement> selectedSettlements = this.tableModel.getSelectedObjects();

        for (Settlement settlement : selectedSettlements) {

            try {
                this.verRecibo(settlement);
            } catch (SQLException e) {
                JOptionPane.showConfirmDialog(this, "No se pudo encontrar el recibo para la liquidación seleccionada");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(this, "No se pudo abrir el recibo para la liquidación seleccionada");
            }
            break;
        }
    }

    private void verRecibo(Settlement settlement) throws SQLException, IOException {

        //guardar desde la liquidacion el pdf al disco
        Blob blob = settlement.getRecibo();
        InputStream fileBlob = blob.getBinaryStream();
        ReadableByteChannel rbc = Channels.newChannel(fileBlob );
        FileOutputStream fos = new FileOutputStream("recibo_tmp.pdf");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();

        //abrir el pdf
        abrirPdf();

    }

    private void abrirPdf() {
        try {
            String pathname = "recibo_tmp.pdf";
            InvoiceFactory.getInstance().openInvoice(pathname);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el recibo");
        }
    }

    protected void btnCancelActionPerformed() {
        this.dispose();
    }

    private void tableMouseClicked(MouseEvent event) {
        int rowIndex = this.table.getSelectedRow();

        if (rowIndex >= 0) {
            this.refreshSelectedObjects();
        }
    }

    private void refreshSelectedObjects() {
        int[] selectedRows = this.table.getSelectedRows();
        this.tableModel.refreshSelectedObjects(selectedRows);
    }

}
