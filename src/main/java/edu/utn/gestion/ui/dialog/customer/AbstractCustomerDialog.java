package edu.utn.gestion.ui.dialog.customer;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Customer;
import edu.utn.gestion.ui.controller.CustomerController;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;
import edu.utn.gestion.ui.util.FormUtils;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

/**
 * Created by martin on 05/12/15.
 */
public abstract class AbstractCustomerDialog extends GenericDialog {
    protected JLabel lblName;
    protected JLabel lblEmail;
    protected JLabel lblCuit;
    protected JLabel lblPhoneNumber;
    protected JTextField txtName;
    protected JTextField txtEmail;
    protected JFormattedTextField txtCuit;
    protected JFormattedTextField txtPhoneNumber;

    protected CustomerController controller;
    protected Customer currentCustomer;

    public AbstractCustomerDialog(JDialog parent, boolean modal, CustomerController controller, Customer customer) {
        super(parent, modal);
        this.controller = controller;
        this.currentCustomer = customer;
        this.initModel();
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {
        if (this.currentCustomer != null) {
            this.txtName.setText(this.currentCustomer.getName());
            this.txtEmail.setText(this.currentCustomer.getEmail());
            this.txtCuit.setText(this.currentCustomer.getCuit());
            this.txtPhoneNumber.setText(this.currentCustomer.getPhoneNumber());
        } else {
            this.currentCustomer = new Customer();
        }
    }

    @Override
    protected void initComponents() {
        this.lblName = new JLabel("Name");
        this.lblEmail = new JLabel("Email");
        this.lblCuit = new JLabel("CUIT");
        this.lblPhoneNumber = new JLabel("Phone Number");

        this.txtName = new JTextField(40);
        this.txtEmail = new JTextField(15);
        this.txtCuit = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("0"))));
        this.txtPhoneNumber = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("0"))));

        this.lblName.setLabelFor(this.txtName);
        this.lblEmail.setLabelFor(this.txtEmail);
        this.lblCuit.setLabelFor(this.txtCuit);
        this.lblPhoneNumber.setLabelFor(this.txtPhoneNumber);

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
    }

    @Override
    protected void initModel() {
        // TODO: add implementation.
    }

    @Override
    protected void setObjectData() throws GestionAppException {
        this.currentCustomer.setName(this.txtName.getText());
        this.currentCustomer.setEmail(this.txtEmail.getText());
        this.currentCustomer.setCuit(this.txtCuit.getText());
        this.currentCustomer.setPhoneNumber(this.txtPhoneNumber.getText());
    }
}
