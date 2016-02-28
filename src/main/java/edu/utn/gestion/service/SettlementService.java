package edu.utn.gestion.service;

import edu.utn.gestion.dao.SettlementDAO;
import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Settlement;
import edu.utn.gestion.service.generic.GenericService;

import java.util.List;

/**
 * Created by ASUS on 25/02/2016.
 */
public class SettlementService extends GenericService<Settlement, Long> {

    private static final SettlementService INSTANCE = new SettlementService();

    private SettlementService() {
        super(SettlementDAO.getInstance());
    }

    public static SettlementService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Settlement> findBySearch(String searchString) throws GestionAppException {
        return null;
    }
}
