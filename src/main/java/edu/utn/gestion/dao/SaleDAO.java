package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Sale;

import java.util.List;

/**
 * Created by martin on 08/12/15.
 */
public class SaleDAO extends GenericDAO<Sale, Long> {
    private static final SaleDAO INSTANCE = new SaleDAO();

    private SaleDAO() {
        super(Sale.class);
    }

    public static SaleDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Sale> findObjectsBySearch(String searchString) throws DataAccessException {
        return null;
    }
}
