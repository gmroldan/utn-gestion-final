package edu.utn.gestion.ui.dialog.supplier;

import edu.utn.gestion.model.Supplier;
import edu.utn.gestion.ui.controller.SupplierController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;

import java.awt.Frame;

/**
 * Created by martin on 18/12/15.
 */
public class SuppliersManagementDialog extends GenericManagementDialog<Supplier, Long> {
    private static final String WINDOW_TITLE = "Suppliers Management";

    /**
     * Creates new form BookManagerDialog.
     *
     * @param parent
     * @param modal
     */
    public SuppliersManagementDialog(Frame parent, boolean modal) {
        super(parent, WINDOW_TITLE, modal, new SupplierController(), new SupplierTableModel());
    }

    @Override
    protected void showEditObjectDialog(Supplier supplier) {
        new EditSupplierDialog(this, true, (SupplierController) this.controller, supplier);
    }

    @Override
    protected void showNewObjectDialog() {
        new NewSupplierDialog(this, true, (SupplierController) this.controller, null);
    }
}
