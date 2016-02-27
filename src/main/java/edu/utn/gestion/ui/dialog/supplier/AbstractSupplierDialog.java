package edu.utn.gestion.ui.dialog.supplier;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Supplier;
import edu.utn.gestion.ui.controller.SupplierController;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;
import edu.utn.gestion.ui.util.FormUtils;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;

/**
 * Created by martin on 18/12/15.
 */
public abstract class AbstractSupplierDialog extends GenericDialog {
    protected JLabel lblName;
    protected JLabel lblEmail;
    protected JTextField txtName;
    protected JTextField txtEmail;

    protected SupplierController controller;
    protected Supplier currentSupplier;

    /**
     * Class constructor.
     *
     * @param parent
     * @param windowTitle
     * @param modal
     * @param controller
     * @param supplier
     */
    public AbstractSupplierDialog(JDialog parent, String windowTitle
            , boolean modal, SupplierController controller, Supplier supplier) {
        super(parent, windowTitle, modal);
        this.controller = controller;
        this.currentSupplier = supplier;
        this.setVisible(true);
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {
        if (this.currentSupplier != null) {
            this.txtName.setText(this.currentSupplier.getName());
            this.txtEmail.setText(this.currentSupplier.getEmail());
        } else {
            this.currentSupplier = new Supplier();
        }
    }

    @Override
    protected void initComponents() {
        this.lblName = new JLabel("Name");
        this.lblEmail = new JLabel("Email");

        this.txtName = new JTextField(25);
        this.txtEmail = new JTextField(30);

        this.lblName.setLabelFor(this.txtName);
        this.lblEmail.setLabelFor(this.txtEmail);

        this.createFormPanel();
    }

    @Override
    protected void createFormPanel() {
        this.formPanel = new JPanel(new GridBagLayout());

        FormUtils.addLabel(this.lblName, this.formPanel);
        FormUtils.addLastField(this.txtName, this.formPanel);
        FormUtils.addLabel(this.lblEmail, this.formPanel);
        FormUtils.addLastField(this.txtEmail, this.formPanel);
    }

    @Override
    protected void initModel() {
        // TODO: add implementation if needed.
    }

    @Override
    protected void setObjectData() throws GestionAppException {
        this.currentSupplier.setName(this.txtName.getText());
        this.currentSupplier.setEmail(this.txtEmail.getText());
    }
}
