package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Settlement;

import java.util.List;

/**
 * Created by ASUS on 25/02/2016.
 */
public class SettlementDAO extends GenericDAO<Settlement, Long> {

    private static final SettlementDAO INSTANCE = new SettlementDAO();

    private SettlementDAO() {
        super(Settlement.class);
    }

    public static SettlementDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Settlement> findObjectsBySearch(String searchString) throws DataAccessException {
        return null;
    }
}
