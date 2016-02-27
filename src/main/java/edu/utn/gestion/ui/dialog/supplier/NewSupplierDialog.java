package edu.utn.gestion.ui.dialog.supplier;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Supplier;
import edu.utn.gestion.ui.controller.SupplierController;
import edu.utn.gestion.ui.util.PopUpFactory;

import javax.swing.JDialog;
import java.awt.event.ActionEvent;

/**
 * Created by martin on 18/12/15.
 */
public class NewSupplierDialog extends AbstractSupplierDialog {
    private static final String WINDOW_TITLE = "New Supplier";

    /**
     * Creates a new instance of <code>NewSupplierDialog</code>
     *
     * @param parent
     * @param modal
     * @param controller
     * @param supplier
     */
    public NewSupplierDialog(JDialog parent, boolean modal, SupplierController controller, Supplier supplier) {
        super(parent, WINDOW_TITLE, modal, controller, supplier);
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        try {
            this.setObjectData();
            this.controller.save(this.currentSupplier);
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
