package edu.utn.gestion.ui.dialog.order;

import edu.utn.gestion.model.Order;
import edu.utn.gestion.ui.controller.OrderController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;
import edu.utn.gestion.ui.dialog.order.table.OrderTableModel;

import javax.swing.JFrame;

/**
 * Created by martin on 25/02/16.
 */
public class OrdersManagementDialog extends GenericManagementDialog<Order, Long> {
    private static final String WINDOW_TITLE = "Orders Management";

    /**
     * Class constructor.
     *
     * @param parent
     */
    public OrdersManagementDialog(JFrame parent) {
        super(parent, WINDOW_TITLE, true, new OrderController(), new OrderTableModel());
    }

    @Override
    protected void showEditObjectDialog(Order order) {
        new ViewOrderDialog(this, (OrderController) this.controller, order).setVisible(true);
    }

    @Override
    protected void showNewObjectDialog() {
        new NewOrderDialog(this, (OrderController) this.controller).setVisible(true);
    }
}
