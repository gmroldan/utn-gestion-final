package edu.utn.gestion.service;

import edu.utn.gestion.dao.SupplierDAO;
import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Supplier;
import edu.utn.gestion.service.generic.GenericService;

import java.util.List;

/**
 * Created by martin on 18/12/15.
 */
public class SupplierService extends GenericService<Supplier, Long> {
    private static final SupplierService INSTANCE = new SupplierService();
    private final SupplierDAO supplierDAO = SupplierDAO.getInstance();

    /**
     * Class constructor.
     */
    private SupplierService() {}

    /**
     * Returns the unique instance of SupplierService.
     * @return
     */
    public static SupplierService getInstance() {
        return INSTANCE;
    }

    @Override
    protected GenericDAO<Supplier, Long> getDAO() {
        return this.supplierDAO;
    }

    @Override
    public List<Supplier> findBySearch(String searchString) throws GestionAppException {
        try {
            return this.supplierDAO.findObjectsBySearch(searchString);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
}
