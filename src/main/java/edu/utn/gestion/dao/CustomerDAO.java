package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Customer;
import java.util.List;

public class CustomerDAO extends GenericDAO<Customer, Long> {
    private static final CustomerDAO INSTANCE = new CustomerDAO();
    
    private CustomerDAO() {
        super(Customer.class);
    }
    
    public static CustomerDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Customer> findBooksBySearch(String searchString) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
