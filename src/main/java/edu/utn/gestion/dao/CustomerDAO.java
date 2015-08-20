package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Customer;
import java.util.List;

public class CustomerDAO extends GenericDAO<Customer, Long> {
    private static final CustomerDAO INSTANCE = new CustomerDAO();
    private final String QUERY_FIND_CUSTOMERS_BY_SEARCH = "from Customer where name like :parm or cuit like :parm";
    
    private CustomerDAO() {
        super(Customer.class);
    }
    
    public static CustomerDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Customer> findObjectsBySearch(String searchString) throws DataAccessException {        
        return this.findObjectsBySearch(QUERY_FIND_CUSTOMERS_BY_SEARCH, searchString);
    }
}
