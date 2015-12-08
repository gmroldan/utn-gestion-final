package edu.utn.gestion.ui.controller;

import edu.utn.gestion.model.Employee;
import edu.utn.gestion.service.EmployeeService;
import edu.utn.gestion.ui.controller.generic.GenericController;

/**
 * Created by martin on 08/12/15.
 */
public class EmployeeController extends GenericController<Employee, Long> {
    /**
     * Creates a new instance of EmployeeController.
     */
    public EmployeeController() {
        super(EmployeeService.getInstance());
    }
}
