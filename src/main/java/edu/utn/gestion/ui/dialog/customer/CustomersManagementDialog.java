package edu.utn.gestion.ui.dialog.customer;

import edu.utn.gestion.model.Customer;
import edu.utn.gestion.ui.controller.CustomerController;
import edu.utn.gestion.ui.controller.generic.GenericController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;
import java.awt.Frame;
import java.awt.event.ItemEvent;

public class CustomersManagementDialog extends GenericManagementDialog<Customer, Long> {
    private static final String WINDOW_TITLE = "Customers Management";
    private final CustomerController controller;

    /**
     * Class constructor.
     *
     * @param parent
     * @param modal
     */
    public CustomersManagementDialog(Frame parent, boolean modal) {
        super(parent, WINDOW_TITLE, modal, new CustomerTableModel());
        this.controller = new CustomerController();
    }

    @Override
    protected void checkBoxChanged(ItemEvent event) {
        // TODO: Add implementation...
    }

    @Override
    protected void showEditObjectDialog(Customer customer) {
        new EditCustomerDialog(this, true, this.controller, customer).setVisible(true);
    }

    @Override
    protected void showNewObjectDialog() {
        new NewCustomerDialog(this, true, this.controller, null).setVisible(true);
    }

    @Override
    protected GenericController<Customer, Long> getController() {
        return this.controller;
    }
}
