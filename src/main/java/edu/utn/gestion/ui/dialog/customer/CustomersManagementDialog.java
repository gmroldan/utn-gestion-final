package edu.utn.gestion.ui.dialog.customer;

import edu.utn.gestion.model.Customer;
import edu.utn.gestion.ui.controller.CustomerController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;
import java.awt.Frame;

public class CustomersManagementDialog extends GenericManagementDialog<Customer, Long> {

    public CustomersManagementDialog(Frame parent, boolean modal) {
        super(parent, modal, new CustomerController(), new CustomerTableModel());
        this.setTitle("Customers Management");
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
