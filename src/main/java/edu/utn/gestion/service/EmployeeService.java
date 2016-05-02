package edu.utn.gestion.service;

import edu.utn.gestion.dao.EmployeeDAO;
import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.service.generic.GenericService;

import java.util.List;

/**
 * Created by martin on 08/12/15.
 */
public class EmployeeService extends GenericService<Employee, Long> {
    private static final EmployeeService INSTANCE = new EmployeeService();
    private final EmployeeDAO employeeDAO = EmployeeDAO.getInstance();

    /**
     * Class constructor.
     */
    private EmployeeService() {}

    /**
     * Returns the unique instance of EmployeeService.
     *
     * @return
     */
    public static EmployeeService getInstance() {
        return INSTANCE;
    }

    @Override
    protected GenericDAO<Employee, Long> getDAO() {
        return this.employeeDAO;
    }

    @Override
    public List<Employee> findBySearch(String searchString) throws GestionAppException {
        try {
            return this.employeeDAO.findObjectsBySearch(searchString);
        } catch (DataAccessException e) {
            throw new GestionAppException(e);
        }
    }
}
