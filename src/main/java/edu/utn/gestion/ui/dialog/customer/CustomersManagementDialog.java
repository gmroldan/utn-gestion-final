package edu.utn.gestion.ui.dialog.customer;

import edu.utn.gestion.model.Customer;
import edu.utn.gestion.ui.controller.CustomerController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;
import java.awt.Frame;

public class CustomersManagementDialog extends GenericManagementDialog<Customer, Long> {
    private static final String WINDOW_TITLE = "Customers Management";

    /**
     * Class constructor.
     *
     * @param parent
     * @param modal
     */
    public CustomersManagementDialog(Frame parent, boolean modal) {
        super(parent, WINDOW_TITLE, modal, new CustomerController(), new CustomerTableModel());
    }

    @Override
    protected void showEditObjectDialog(Customer customer) {
        new EditCustomerDialog(this, true, (CustomerController) this.controller, customer).setVisible(true);
    }

    @Override
    protected void showNewObjectDialog() {
        new NewCustomerDialog(this, true, (CustomerController) this.controller, null).setVisible(true);
    }
    
}
