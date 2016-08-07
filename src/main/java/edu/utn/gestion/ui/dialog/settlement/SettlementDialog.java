package edu.utn.gestion.ui.dialog.settlement;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.service.util.liquidacion.LiquidadorDeSueldos;
import edu.utn.gestion.service.util.liquidacion.LiquidadorWorker;
import edu.utn.gestion.ui.MainFrame;
import edu.utn.gestion.ui.controller.EmployeeController;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;
import edu.utn.gestion.ui.internal.EmployeeForSettlementTableModel;
import edu.utn.gestion.ui.util.FormUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 23/02/2016.
 */
public class SettlementDialog extends GenericDialog{

    private JLabel lblPeriod;
    private JTable table;
    private EmployeeForSettlementTableModel tableModel;

    private JComboBox cmbMonths;

    private JComboBox cmbYears;
    private EmployeeController controller;
    private JButton buscarBtn;

    public SettlementDialog(MainFrame parent, boolean modal) {
        super(parent, modal);
//        this.setSize(800,600);
        this.controller = new EmployeeController();
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {

    }

    @Override
    protected void initComponents() {
        lblPeriod = new JLabel("Period");

        cmbMonths = new JComboBox();
        cmbYears = new JComboBox();

        buscarBtn = new JButton("Buscar");
        this.buscarBtn.addActionListener(event -> this.buscarBtnActionPerformed());
        this.table = new JTable();
        this.tableModel = new EmployeeForSettlementTableModel();
        this.table.setModel(this.tableModel);


        initModel();

        resetComponents();

        createFormPanel();
    }

    private void resetComponents() {
    }

    @Override
    protected void createFormPanel() {

        this.formPanel = new JPanel(new GridBagLayout());

        FormUtils.addLabel(this.lblPeriod, this.formPanel);
        FormUtils.addMiddleField(this.cmbMonths, this.formPanel);
        FormUtils.addLastField(this.cmbYears, this.formPanel);
        FormUtils.addLastField(this.buscarBtn, this.formPanel);

        FormUtils.addMiddleField(table,this.formPanel);

        this.btnAccept.setText("Liquidar");
        this.btnCancel.setText("Volver");

        this.setResizable(true);
    }

    @Override
    protected void initModel() {

        Date date = new Date();
        int year = date.getYear()+1900;

        ArrayList<Integer> years = new ArrayList<Integer>();
        for (int i=year-5;i<=year;i++) {
            years.add(i);
        }
        this.cmbYears.setModel(new DefaultComboBoxModel(years.toArray()));
        this.cmbYears.setSelectedItem(year);


        ArrayList<Integer> months = new ArrayList<Integer>();
        for (int i=1;i<13;i++) {
            months.add(i);
        }
        this.cmbMonths.setModel(new DefaultComboBoxModel(months.toArray()));
        this.cmbMonths.setSelectedItem(date.getMonth()+1);

    }

    @Override
    protected void setObjectData() throws GestionAppException {

    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        this.setEnabled(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        List<Employee> employees = this.tableModel.getObjectList();

        String period = this.cmbYears.getSelectedItem().toString() +
                "-" + this.cmbMonths.getSelectedItem().toString();

        new LiquidadorWorker(employees,period,this).execute();

    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {

        this.setVisible(false);
    }

    private void updateObjectList() throws GestionAppException {
        List<Employee> employees = this.controller.findAll();
        List<Employee> filterEmp = new ArrayList<>();
        String period = this.cmbYears.getSelectedItem().toString() +
                "-" + this.cmbMonths.getSelectedItem().toString();

        for (Employee e : employees) {

            if (LiquidadorDeSueldos.isEmployeeAvailableForPeriod(e,period)) {
                filterEmp.add(e);
            }
        }

        this.tableModel.setObjectList(filterEmp);
    }

    private void buscarBtnActionPerformed() {
        try {
            this.updateObjectList();
        } catch (GestionAppException e) {
            JOptionPane.showMessageDialog(this,"No se pudieron recuperar los empleados para ese periodo");
        }
    }
}
