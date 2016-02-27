package edu.utn.gestion.ui.dialog.employee;

import edu.utn.gestion.model.Employee;
import edu.utn.gestion.ui.controller.EmployeeController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;

import java.awt.Frame;

/**
 * Created by martin on 08/12/15.
 */
public class EmployeesManagementDialog extends GenericManagementDialog<Employee, Long> {
    private static final String WINDOW_TITLE = "Employees Management";

    /**
     * Class constructor.
     *
     * @param parent
     * @param modal
     */
    public EmployeesManagementDialog(Frame parent, boolean modal) {
        super(parent, WINDOW_TITLE, modal, new EmployeeController(), new EmployeeTableModel());
    }

    @Override
    protected void showEditObjectDialog(Employee employee) {
        new EditEmployeeDialog(this, true, (EmployeeController) this.controller, employee).setVisible(true);
    }

    @Override
    protected void showNewObjectDialog() {
        new NewEmployeeDialog(this, true, (EmployeeController) this.controller, null).setVisible(true);
    }
}
