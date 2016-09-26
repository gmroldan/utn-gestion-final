package edu.utn.gestion.ui.dialog.settlement;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.service.util.liquidacion.LiquidadorDeSueldos;
import edu.utn.gestion.ui.util.worker.LiquidadorWorker;
import edu.utn.gestion.ui.MainFrame;
import edu.utn.gestion.ui.controller.EmployeeController;
import edu.utn.gestion.ui.internal.EmployeeForSettlementTableModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 23/02/2016.
 */
public class SettlementDialog extends JDialog{

    public static final String WINDOWS_TITLE = "Liquidación por lotes";
    private JPanel panel;
    private JLabel lblPeriod;
    private JTable table;
    private EmployeeForSettlementTableModel tableModel;

    private JComboBox cmbMonths;

    private JComboBox cmbYears;
    private EmployeeController controller;
    private JButton buscarBtn;

    private JButton liquidarBtn;
    private JButton cancelarBtn;

    public SettlementDialog(MainFrame parent, boolean modal) {
        super(parent, modal);
        this.controller = new EmployeeController();
        this.setSize(300,300);
        this.setTitle(WINDOWS_TITLE);

        initComponents();
    }

    protected void initComponents() {
        lblPeriod = new JLabel("Period");

        cmbMonths = new JComboBox();
        cmbYears = new JComboBox();

        buscarBtn = new JButton("Buscar");
        this.buscarBtn.addActionListener(event -> this.buscarBtnActionPerformed());
        this.table = new JTable();
        this.tableModel = new EmployeeForSettlementTableModel();
        this.table.setModel(this.tableModel);

        this.liquidarBtn = new JButton("Liquidar");
        this.liquidarBtn.addActionListener(event -> this.liquidarBtnActionPerformed());
        this.cancelarBtn = new JButton("Cancelar");
        this.cancelarBtn.addActionListener(event -> this.cancelarBtnActionPerformed());

        initModel();

        resetComponents();

        createFormPanel();
    }

    private void resetComponents() {
    }

    protected void createFormPanel() {

        this.panel = new JPanel(new BorderLayout());

        JPanel startPage = new JPanel(new FlowLayout());
        startPage.add(this.lblPeriod);
        startPage.add(this.cmbMonths);
        startPage.add(this.cmbYears);
        startPage.add(this.cancelarBtn);
        startPage.add(this.buscarBtn);

        this.panel.add(startPage,BorderLayout.PAGE_START);

        JScrollPane scrollPane = new JScrollPane(this.table);
        scrollPane.setSize(400,200);
        this.panel.add(scrollPane, BorderLayout.CENTER);

        JPanel endPage = new JPanel(new FlowLayout());
        endPage.add(this.liquidarBtn);
        endPage.add(this.cancelarBtn);

        this.panel.add(endPage,BorderLayout.PAGE_END);

        this.setResizable(true);
        this.setContentPane(this.panel);
    }

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

    private void liquidarBtnActionPerformed() {
        String period = this.cmbYears.getSelectedItem().toString() +
                "-" + this.cmbMonths.getSelectedItem().toString();

        List<Employee> employees = this.tableModel.getObjectList();

        if (employees.size() < 1) {
            JOptionPane.showMessageDialog(this, "Seleccione un periodo con al menos un empleado");

        } else {
            int result = JOptionPane.showConfirmDialog(this, "Iniciar liquidación para el periodo: " + period);

            if (result != JOptionPane.OK_OPTION) {
                return ;
            }

            this.setEnabled(false);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            new LiquidadorWorker(employees,period,this).execute();
        }
    }

    private void cancelarBtnActionPerformed() {
        this.dispose();
    }
}
