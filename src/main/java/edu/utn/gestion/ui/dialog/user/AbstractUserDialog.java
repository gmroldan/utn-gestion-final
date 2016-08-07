package edu.utn.gestion.ui.dialog.user;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.User;
import edu.utn.gestion.model.UserRole;
import edu.utn.gestion.ui.constants.UIConstants;
import edu.utn.gestion.ui.controller.UserController;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;
import edu.utn.gestion.ui.util.FormUtils;
import edu.utn.gestion.ui.util.IconFactory;
import edu.utn.gestion.ui.util.PopUpFactory;
import org.apache.commons.lang3.StringUtils;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;

/**
 * Created by martin on 07/08/16.
 */
public abstract class AbstractUserDialog extends GenericDialog {
    protected JLabel lblName;
    protected JLabel lblPassword;
    protected JLabel lblRole;
    protected JLabel lblEmployee;
    protected JTextField txtName;
    protected JPasswordField txtPassword;
    protected JTextField txtEmployee;
    protected JComboBox cmbRole;
    protected JButton btnSearchEmployee;
    protected JPanel panelEmployee;

    protected UserController controller;
    protected User currentUser;

    /**
     * Class contructor.
     *
     * @param parent
     * @param windowTitle
     * @param controller
     * @param user
     */
    public AbstractUserDialog(JDialog parent, String windowTitle
            , UserController controller, User user) {
        super(parent, windowTitle, true);
        this.controller = controller;
        this.initModel();
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {
        this.currentUser = new User();
    }


    @Override
    protected void initComponents() {
        this.panelEmployee = new JPanel(new FlowLayout());

        this.lblName = new JLabel("Name");
        this.lblPassword = new JLabel("Password");
        this.lblRole = new JLabel("Role");
        this.lblEmployee = new JLabel("Employee");

        this.txtName = new JTextField();
        this.txtPassword = new JPasswordField();
        this.cmbRole = new JComboBox(new DefaultComboBoxModel(UserRole.values()));
        this.txtEmployee = new JTextField(10);

        this.btnSearchEmployee
                = new JButton(IconFactory.getIcon(UIConstants.ICON_DOCUMENT_SEARCH_LOCATION));

        this.panelEmployee.add(this.txtEmployee);
        this.panelEmployee.add(this.btnSearchEmployee);

        this.lblName.setLabelFor(this.txtName);
        this.lblPassword.setLabelFor(this.txtPassword);
        this.lblRole.setLabelFor(this.cmbRole);
        this.lblEmployee.setLabelFor(this.panelEmployee);

        this.txtEmployee.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent event) {
                setEmployee();
            }
        });

        this.btnSearchEmployee.addActionListener(event -> this.searchEmployee());

        this.createFormPanel();
    }

    @Override
    protected void createFormPanel() {
        this.formPanel = new JPanel(new GridBagLayout());

        FormUtils.addLabel(this.lblName, this.formPanel);
        FormUtils.addLastField(this.txtName, this.formPanel);
        FormUtils.addLabel(this.lblPassword, this.formPanel);
        FormUtils.addLastField(this.txtPassword, this.formPanel);
        FormUtils.addLabel(this.lblRole, this.formPanel);
        FormUtils.addLastField(this.cmbRole, this.formPanel);
        FormUtils.addLabel(this.lblEmployee, this.formPanel);
        FormUtils.addLastField(this.panelEmployee, this.formPanel);
    }

    @Override
    protected void initModel() {
        // TODO: add implementation.
    }

    @Override
    protected void setObjectData() throws GestionAppException {
        this.validateFields();
        this.currentUser.setName(this.txtName.getText());
        this.currentUser.setPassword(this.txtPassword.getText());
        this.currentUser.setUserRole((UserRole) this.cmbRole.getSelectedItem());
    }

    protected void validateFields() throws GestionAppException {
        String message = null;

        if (StringUtils.isEmpty(this.txtName.getText())) {
            message = "Name cannot be empty.";
        } else if (StringUtils.isEmpty(this.txtPassword.getText())) {
            message = "Password cannot be empty.";
        } else if (StringUtils.isEmpty(this.txtEmployee.getText())) {
            message = "Employee cannot be empty.";
        } else {
            return;
        }

        throw new GestionAppException(message);
    }

    private void setEmployee() {
        String employeeIdString = this.txtEmployee.getText();

        if (StringUtils.isNotEmpty(employeeIdString)) {
            long employeeId = Long.valueOf(employeeIdString);
            Employee employee = null;

            try {
                employee = this.controller.getEmployee(employeeId);
            } catch (GestionAppException ex) {
                PopUpFactory.showInfoMessage(this, ex.getMessage());
            }

            if (employee != null) {
                this.currentUser.setEmployee(employee);
            }
        } else {
            PopUpFactory.showInfoMessage(this, "Employee cannot be empty.");
        }
    }

    private void searchEmployee() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
