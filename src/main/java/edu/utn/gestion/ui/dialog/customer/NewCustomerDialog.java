package edu.utn.gestion.ui.dialog.customer;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Customer;
import edu.utn.gestion.ui.controller.CustomerController;
import edu.utn.gestion.ui.util.PopUpFactory;

import javax.swing.JDialog;
import java.awt.event.ActionEvent;

/**
 * Created by martin on 05/12/15.
 */
public class NewCustomerDialog extends AbstractCustomerDialog {
    public NewCustomerDialog(JDialog parent, boolean modal, CustomerController controller, Customer customer) {
        super(parent, modal, controller, customer);
        this.setTitle("New Customer");
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        try {
            this.setObjectData();
            this.controller.save(this.currentCustomer);
            this.dispose();
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
        }
    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {
        this.dispose();
    }
}
