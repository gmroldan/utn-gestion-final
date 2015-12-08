package edu.utn.gestion.ui.dialog.employee;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.GenderEnum;
import edu.utn.gestion.ui.controller.EmployeeController;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;
import edu.utn.gestion.ui.util.FormUtils;
import edu.utn.gestion.ui.util.IFormat;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

/**
 * Created by martin on 08/12/15.
 */
public abstract class AbstractEmployeeDialog extends GenericDialog {
    protected JLabel lblName;
    protected JLabel lblEmail;
    protected JLabel lblCuit;
    protected JLabel lblPhoneNumber;
    protected JLabel lblAddress;
    protected JLabel lblGender;
    protected JTextField txtName;
    protected JTextField txtEmail;
    protected JTextField txtAddress;
    protected JFormattedTextField txtCuit;
    protected JFormattedTextField txtPhoneNumber;
    protected JComboBox<GenderEnum> cmbGender;

    protected EmployeeController controller;
    protected Employee currentEmployee;

    public AbstractEmployeeDialog(JDialog parent, boolean modal, EmployeeController controller, Employee employee) {
        super(parent, modal);
        this.controller = controller;
        this.currentEmployee = employee;
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {
        if (this.currentEmployee != null) {
            this.txtName.setText(this.currentEmployee.getName());
            this.txtEmail.setText(this.currentEmployee.getEmail());
            this.txtCuit.setText(this.currentEmployee.getCuit());
            this.txtPhoneNumber.setText(this.currentEmployee.getPhoneNumber());
            this.txtAddress.setText(this.currentEmployee.getAddress());
            this.cmbGender.setSelectedItem(this.currentEmployee.getGender());
        } else {
            this.currentEmployee = new Employee();
        }
    }

    @Override
    protected void initComponents() {
        this.lblName = new JLabel("Name");
        this.lblEmail = new JLabel("Email");
        this.lblCuit = new JLabel("CUIT");
        this.lblPhoneNumber = new JLabel("Phone Number");
        this.lblAddress = new JLabel("Address");
        this.lblGender = new JLabel("Gender");

        this.txtName = new JTextField(40);
        this.txtEmail = new JTextField(15);
        this.txtCuit = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat(IFormat.NUMERIC_FORMAT))));
        this.txtPhoneNumber = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat(IFormat.NUMERIC_FORMAT))));
        this.txtAddress = new JTextField(30);
        this.cmbGender = new JComboBox<GenderEnum>(GenderEnum.values());

        this.lblName.setLabelFor(this.txtName);
        this.lblEmail.setLabelFor(this.txtEmail);
        this.lblCuit.setLabelFor(this.txtCuit);
        this.lblPhoneNumber.setLabelFor(this.txtPhoneNumber);
        this.lblAddress.setLabelFor(this.txtAddress);
        this.lblGender.setLabelFor(this.cmbGender);

        this.createFormPanel();
    }

    @Override
    protected void createFormPanel() {
        this.formPanel = new JPanel(new GridBagLayout());

        FormUtils.addLabel(this.lblName, this.formPanel);
        FormUtils.addLastField(this.txtName, this.formPanel);
        FormUtils.addLabel(this.lblEmail, this.formPanel);
        FormUtils.addLastField(this.txtEmail, this.formPanel);
        FormUtils.addLabel(this.lblCuit, this.formPanel);
        FormUtils.addLastField(this.txtCuit, this.formPanel);
        FormUtils.addLabel(this.lblPhoneNumber, this.formPanel);
        FormUtils.addLastField(this.txtPhoneNumber, this.formPanel);
        FormUtils.addLabel(this.lblAddress, this.formPanel);
        FormUtils.addLastField(this.txtAddress, this.formPanel);
        FormUtils.addLabel(this.lblGender, this.formPanel);
        FormUtils.addLastField(this.cmbGender, this.formPanel);
    }

    @Override
    protected void initModel() {
        // TODO: add implementation if needed.
    }

    @Override
    protected void setObjectData() throws GestionAppException {
        this.currentEmployee.setName(this.txtName.getText());
        this.currentEmployee.setEmail(this.txtEmail.getText());
        this.currentEmployee.setCuit(this.txtCuit.getText());
        this.currentEmployee.setPhoneNumber(this.txtPhoneNumber.getText());
        this.currentEmployee.setAddress(this.txtAddress.getText());
        this.currentEmployee.setGender((GenderEnum) this.cmbGender.getSelectedItem());
    }
}
