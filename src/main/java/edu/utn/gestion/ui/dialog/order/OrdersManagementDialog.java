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
     * @param modal
     */
    public OrdersManagementDialog(JFrame parent, boolean modal) {
        super(parent, WINDOW_TITLE, true, new OrderController(), new OrderTableModel());
    }

    @Override
    protected void showEditObjectDialog(Order object) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    protected void showNewObjectDialog() {
        new NewOrderDialog(this, true, (OrderController) this.controller).setVisible(true);
    }
}
