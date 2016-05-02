package edu.utn.gestion.service;

import edu.utn.gestion.dao.CustomerDAO;
import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Customer;
import edu.utn.gestion.service.generic.GenericService;
import java.util.List;

public class CustomerService extends GenericService<Customer, Long> {
    private static final CustomerService INSTANCE = new CustomerService();
    private final CustomerDAO customerDAO = CustomerDAO.getInstance();

    /**
     * Class constructor.
     */
    private CustomerService() {}

    /**
     * Returns the unique instance of CustomerService.
     *
     * @return
     */
    public static CustomerService getInstance() {
        return INSTANCE;
    }

    @Override
    protected GenericDAO<Customer, Long> getDAO() {
        return this.customerDAO;
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
