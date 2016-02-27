package edu.utn.gestion.ui.dialog.employee;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.ui.controller.EmployeeController;
import edu.utn.gestion.ui.util.PopUpFactory;

import javax.swing.JDialog;
import java.awt.event.ActionEvent;

/**
 * Created by martin on 08/12/15.
 */
public class NewEmployeeDialog extends AbstractEmployeeDialog {
    private static final String WINDOW_TITLE = "New Employee";

    /**
     * Creates a new instance of NewEmployeeDialog.
     *
     * @param parent
     * @param modal
     * @param controller
     * @param employee
     */
    public NewEmployeeDialog(JDialog parent, boolean modal, EmployeeController controller, Employee employee) {
        super(parent, WINDOW_TITLE, modal, controller, employee);
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        try {
            this.setObjectData();
            this.controller.save(this.currentEmployee);
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
