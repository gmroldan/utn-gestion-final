package edu.utn.gestion.ui.dialog.settlement;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Attendance;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.ui.MainFrame;
import edu.utn.gestion.ui.controller.EmployeeController;
import edu.utn.gestion.ui.dialog.employee.ComboEmployees;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;
import edu.utn.gestion.ui.util.FormUtils;
import edu.utn.gestion.ui.util.PopUpFactory;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ASUS on 23/02/2016.
 */
public class AttendanceDialog extends GenericDialog{

    private JLabel lblEmployee;
    private JLabel lblPeriod;

    private ComboEmployees cmbEmployees;
    private JComboBox cmbMonths;
    private JComboBox cmbYears;

    private JCheckBox checkPresenteeism;
    private JSpinner spinnerUnAttendance;

    private EmployeeController controller;

    public AttendanceDialog(MainFrame parent, boolean modal) {
        super(parent, modal);
        controller = new EmployeeController();
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {

    }

    @Override
    protected void initComponents() {
        lblEmployee = new JLabel("Employee");
        lblPeriod = new JLabel("Period");

        cmbEmployees = new ComboEmployees();
        cmbEmployees.addItemListener(e -> resetComponents());
        cmbMonths = new JComboBox();
        cmbYears = new JComboBox();

        initModel();

        checkPresenteeism = new JCheckBox("Presenteeism");
        SpinnerModel model = new SpinnerNumberModel(0,0,30,1);
        spinnerUnAttendance = new JSpinner(model);

        resetComponents();

        createFormPanel();
    }

    private void resetComponents() {

        checkPresenteeism.setSelected(true);
        checkPresenteeism.addActionListener(e -> {
            if (spinnerUnAttendance.isEnabled() == true) {
                spinnerUnAttendance.setEnabled(false);
            } else {
                spinnerUnAttendance.setEnabled(true);
            }
        });

        spinnerUnAttendance.getModel().setValue(0);
        spinnerUnAttendance.setEnabled(false);
    }

    @Override
    protected void createFormPanel() {

        this.formPanel = new JPanel(new GridBagLayout());

        FormUtils.addLabel(this.lblEmployee, this.formPanel);
        FormUtils.addLastField(this.cmbEmployees, this.formPanel);

        FormUtils.addLabel(this.lblPeriod, this.formPanel);
        FormUtils.addMiddleField(this.cmbMonths, this.formPanel);
        FormUtils.addLastField(this.cmbYears, this.formPanel);

        FormUtils.addLabel(new JLabel("       "),this.formPanel);
        FormUtils.addLastField(new JLabel("       "),this.formPanel);

        FormUtils.addLabel(this.checkPresenteeism,this.formPanel);
        FormUtils.addMiddleField(new JLabel("  Un Attendances"),this.formPanel);
        FormUtils.addLastField(this.spinnerUnAttendance, this.formPanel);

        FormUtils.addLabel(new JLabel("       "),this.formPanel);
        FormUtils.addLastField(new JLabel("       "),this.formPanel);

        this.btnAccept.setText("Save");
        this.btnCancel.setText("Go Back");
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

        String period = cmbYears.getSelectedItem().toString() + "-" + cmbMonths.getSelectedItem().toString();
        Employee employee = this.cmbEmployees.getEmployee();
        Attendance attendance = new Attendance(period,employee.getId());
        boolean presenteeism = this.checkPresenteeism.isSelected();
        attendance.setPresenteeism(presenteeism);
        if (presenteeism) {
            attendance.setAbsences(0);
        } else {
            int absences = (int) this.spinnerUnAttendance.getModel().getValue();
            attendance.setAbsences(absences);
        }

        employee.getAttendances().put(attendance.getId(),attendance);

        try {
            this.controller.update(employee);
        } catch (GestionAppException e) {
            PopUpFactory.showErrorMessage(this, e.getMessage());
        }

    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {

    }
}
