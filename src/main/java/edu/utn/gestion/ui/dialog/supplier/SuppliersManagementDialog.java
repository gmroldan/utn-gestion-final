package edu.utn.gestion.ui.dialog.supplier;

import edu.utn.gestion.model.Supplier;
import edu.utn.gestion.ui.controller.SupplierController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;

import java.awt.Frame;

/**
 * Created by martin on 18/12/15.
 */
public class SuppliersManagementDialog extends GenericManagementDialog<Supplier, Long> {
    /**
     * Creates new form BookManagerDialog.
     *
     * @param parent
     * @param modal
     */
    public SuppliersManagementDialog(Frame parent, boolean modal) {
        super(parent, modal, new SupplierController(), new SupplierTableModel());
    }

    @Override
    protected void showEditObjectDialog(Supplier object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void showNewObjectDialog() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
