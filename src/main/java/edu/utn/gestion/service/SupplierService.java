package edu.utn.gestion.service;

import edu.utn.gestion.dao.SupplierDAO;
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

    private SupplierService() {
        super(SupplierDAO.getInstance());
    }

    public static SupplierService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Supplier> findBySearch(String searchString) throws GestionAppException {
        try {
            return this.genericDAO.findObjectsBySearch(searchString);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
}
