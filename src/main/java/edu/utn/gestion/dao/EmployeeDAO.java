package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Employee;

import java.util.List;

/**
 * Created by martin on 08/12/15.
 */
public class EmployeeDAO extends GenericDAO<Employee, Long> {
    private static final  EmployeeDAO INSTANCE = new EmployeeDAO();

    private EmployeeDAO() {
        super(Employee.class);
    }

    public static EmployeeDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Employee> findObjectsBySearch(String searchString) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
