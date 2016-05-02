package edu.utn.gestion.ui.controller;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Order;
import edu.utn.gestion.model.OrderDetail;
import edu.utn.gestion.service.OrderService;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.ui.controller.generic.GenericController;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * Created by martin on 08/02/16.
 */
public class OrderController extends GenericController<Order, Long> {
    private final OrderService service = OrderService.getInstance();

    public List<OrderDetail> getBooksForNewOrder() throws GestionAppException {
        return this.service.getBooksForNewOrder();
    }

    public long save(List<OrderDetail> orderDetailList) throws GestionAppException {
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new GestionAppException("Cannot create an order with no books.");
        }

        return this.service.createNewOrder(orderDetailList);
    }

    @Override
    protected GenericService<Order, Long> getService() {
        return this.service;
    }
}
