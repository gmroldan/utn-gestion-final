package edu.utn.gestion.service;

import edu.utn.gestion.dao.CustomerDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Customer;
import edu.utn.gestion.service.generic.GenericService;
import java.util.List;

public class CustomerService extends GenericService<Customer, Long> {
    private static final CustomerService INSTANCE = new CustomerService();
    private final CustomerDAO customerDAO;
    
    private CustomerService() {
        super(CustomerDAO.getInstance());
        this.customerDAO = (CustomerDAO) this.genericDAO;
    }
    
    public static CustomerService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Customer> findBySearch(String searchString) throws GestionAppException {
        try {
            return this.customerDAO.findObjectsBySearch(searchString);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage());
        }
    }
}
