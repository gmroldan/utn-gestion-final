package edu.utn.gestion.service;

import edu.utn.gestion.dao.SaleDAO;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Sale;
import edu.utn.gestion.service.generic.GenericService;

import java.util.List;

/**
 * Created by martin on 08/12/15.
 */
public class SaleService extends GenericService<Sale, Long> {
    private static final SaleService INSTANCE = new SaleService();

    private SaleService() {
        super(SaleDAO.getInstance());
    }

    public static SaleService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Sale> findBooksBySearch(String searchString) throws GestionAppException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
