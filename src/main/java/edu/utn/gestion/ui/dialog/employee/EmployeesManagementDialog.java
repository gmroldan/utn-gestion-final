package edu.utn.gestion.ui.dialog.employee;

import edu.utn.gestion.model.Employee;
import edu.utn.gestion.ui.controller.EmployeeController;
import edu.utn.gestion.ui.controller.generic.GenericController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;

import java.awt.Frame;
import java.awt.event.ItemEvent;

/**
 * Created by martin on 08/12/15.
 */
public class EmployeesManagementDialog extends GenericManagementDialog<Employee, Long> {
    private static final String WINDOW_TITLE = "Employees Management";
    private final EmployeeController controller;

    /**
     * Class constructor.
     *
     * @param parent
     * @param modal
     */
    public EmployeesManagementDialog(Frame parent, boolean modal) {
        super(parent, WINDOW_TITLE, modal, new EmployeeTableModel());
        this.controller = new EmployeeController();
    }

    @Override
    protected void checkBoxChanged(ItemEvent event) {
        // TODO: Add implementation...
    }

    @Override
    protected void showEditObjectDialog(Employee employee) {
        new EditEmployeeDialog(this, true, this.controller, employee).setVisible(true);
    }

    @Override
    protected void showNewObjectDialog() {
        new NewEmployeeDialog(this, true, this.controller, null).setVisible(true);
    }

    @Override
    protected GenericController<Employee, Long> getController() {
        return this.controller;
    }
}
