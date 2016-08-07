package edu.utn.gestion.ui.dialog.supplier;

import edu.utn.gestion.model.Supplier;
import edu.utn.gestion.ui.controller.SupplierController;
import edu.utn.gestion.ui.controller.generic.GenericController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;

import java.awt.Frame;
import java.awt.event.ItemEvent;

/**
 * Created by martin on 18/12/15.
 */
public class SuppliersManagementDialog extends GenericManagementDialog<Supplier, Long> {
    private static final String WINDOW_TITLE = "Suppliers Management";
    private final SupplierController controller;

    /**
     * Creates new form BookManagerDialog.
     *
     * @param parent
     * @param modal
     */
    public SuppliersManagementDialog(Frame parent, boolean modal) {
        super(parent, WINDOW_TITLE, modal, new SupplierTableModel());
        this.controller = new SupplierController();
    }

    @Override
    protected void checkBoxChanged(ItemEvent event) {
        // TODO: Add implementation...
    }

    @Override
    protected void showEditObjectDialog(Supplier supplier) {
        new EditSupplierDialog(this, true, this.controller, supplier);
    }

    @Override
    protected void showNewObjectDialog() {
        new NewSupplierDialog(this, true, this.controller, null);
    }

    @Override
    protected GenericController<Supplier, Long> getController() {
        return this.controller;
    }
}
