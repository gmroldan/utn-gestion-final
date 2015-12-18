package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Supplier;

import java.util.List;

/**
 * Created by martin on 18/12/15.
 */
public class SupplierDAO extends GenericDAO<Supplier, Long> {
    private static final SupplierDAO INSTANCE = new SupplierDAO();
    private final String QUERY_FIND_SUPPLIERS_BY_SEARCH = "from Supplier where name like :parm";

    private SupplierDAO() {
        super(Supplier.class);
    }

    public static SupplierDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Supplier> findObjectsBySearch(String searchString) throws DataAccessException {
        return this.findObjectsBySearch(QUERY_FIND_SUPPLIERS_BY_SEARCH, searchString);
    }
}
