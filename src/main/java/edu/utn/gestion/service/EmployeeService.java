package edu.utn.gestion.service;

import edu.utn.gestion.dao.EmployeeDAO;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.service.generic.GenericService;

import java.util.List;

/**
 * Created by martin on 08/12/15.
 */
public class EmployeeService extends GenericService<Employee, Long> {
    private static final EmployeeService INSTANCE = new EmployeeService();
    private final EmployeeDAO employeeDAO;

    public EmployeeService() {
        super(EmployeeDAO.getInstance());
        this.employeeDAO = (EmployeeDAO) this.genericDAO;
    }

    public static EmployeeService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Employee> findBooksBySearch(String searchString) throws GestionAppException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
