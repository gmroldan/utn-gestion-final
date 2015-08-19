package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.model.Customer;

public class CustomerDAO extends GenericDAO<Customer, Long> {
    private static final CustomerDAO INSTANCE = new CustomerDAO();
    
    private CustomerDAO() {
        super(Customer.class);
    }
    
    public static CustomerDAO getInstance() {
        return INSTANCE;
    }
}
