package edu.utn.gestion.ui.controller;

import edu.utn.gestion.model.Employee;
import edu.utn.gestion.service.EmployeeService;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.ui.controller.generic.GenericController;

/**
 * Created by martin on 08/12/15.
 */
public class EmployeeController extends GenericController<Employee, Long> {
    private final EmployeeService service = EmployeeService.getInstance();

    @Override
    protected GenericService<Employee, Long> getService() {
        return this.service;
    }
}
