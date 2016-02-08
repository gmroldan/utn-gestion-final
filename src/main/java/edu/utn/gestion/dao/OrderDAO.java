package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Order;

import java.util.List;

/**
 * Created by martin on 26/12/15.
 */
public class OrderDAO extends GenericDAO<Order, Long> {
    private static final OrderDAO INSTANCE = new OrderDAO();

    /**
     * Class constructor.
     *
     */
    private OrderDAO() {
        super(Order.class);
    }

    public static OrderDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Order> findObjectsBySearch(String searchString) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
