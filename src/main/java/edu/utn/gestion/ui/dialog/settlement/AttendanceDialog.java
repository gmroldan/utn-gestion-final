package edu.utn.gestion.ui.dialog.settlement;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Settlement;
import edu.utn.gestion.ui.MainFrame;
import edu.utn.gestion.ui.controller.SettlementController;
import edu.utn.gestion.ui.dialog.employee.ComboEmployees;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;
import edu.utn.gestion.ui.util.FormUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
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
public class AttendanceDialog extends GenericDialog{

    private JLabel lblEmployee;

    private ComboEmployees cmbEmployees;

    private JTable table;
    private SettlementTableModel tableModel;

    private SettlementController controller;

    public AttendanceDialog(MainFrame parent, boolean modal) {
        super(parent, modal);
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {

    }

    @Override
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

        initModel();

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

    @Override
    protected void createFormPanel() {

        this.formPanel = new JPanel(new GridBagLayout());

        FormUtils.addLabel(this.lblEmployee, this.formPanel);
        FormUtils.addMiddleField(this.cmbEmployees, this.formPanel);
        FormUtils.addLastField(new JLabel("           "), this.formPanel);
        FormUtils.addLastField(new JLabel("           "), this.formPanel);
        FormUtils.addMiddleField(table,this.formPanel);

        this.btnAccept.setText("Ver Recibo");
        this.btnCancel.setText("Volver");
    }

    @Override
    protected void initModel() {



    }

    @Override
    protected void setObjectData() throws GestionAppException {

    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {

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

            String pathname = "C:\\Users\\ASUS\\Documents\\Proyectos_IntelliJ\\utn-gestion-final\\recibo_tmp.pdf";
            if ((new File(pathname)).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler " + pathname);
                p.waitFor();

            } else {
                System.out.println("File is not exists");

            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {

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
