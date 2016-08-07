package edu.utn.gestion.ui.controller;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.User;
import edu.utn.gestion.service.EmployeeService;
import edu.utn.gestion.service.UserService;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.ui.controller.generic.GenericController;

/**
 * Created by martin on 26/07/16.
 */
public class UserController extends GenericController<User, Long> {
    private final UserService userService = UserService.getInstance();
    private final EmployeeService employeeService = EmployeeService.getInstance();

    @Override
    protected GenericService<User, Long> getService() {
        return this.userService;
    }

    public User login(final String userName, final String password)
            throws GestionAppException {
        return this.userService.login(userName, password);
    }

    public Employee getEmployee(final long employeeId) throws GestionAppException {
        return this.employeeService.findOne(employeeId);
    }

    public User resetPassword(final User user) throws GestionAppException {
        return this.userService.resetPassword(user);
    }
}
