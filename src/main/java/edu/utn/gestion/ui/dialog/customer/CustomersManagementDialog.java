package edu.utn.gestion.ui.dialog.customer;

import edu.utn.gestion.model.Customer;
import edu.utn.gestion.ui.controller.CustomerController;
import edu.utn.gestion.ui.util.GenericManagementDialog;
import java.awt.Frame;

public class CustomersManagementDialog extends GenericManagementDialog<Customer, Long> {

    public CustomersManagementDialog(Frame parent, boolean modal) {
        super(parent, modal, new CustomerController(), new CustomerTableModel());
        this.setTitle("Customers Management");
    }

    @Override
    protected void showEditObjectDialog(Customer object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void showNewObjectDialog() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
