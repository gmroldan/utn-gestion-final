package edu.utn.gestion.ui.dialog.order;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Order;
import edu.utn.gestion.model.OrderDetail;
import edu.utn.gestion.ui.controller.OrderController;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;
import edu.utn.gestion.ui.dialog.order.table.OrderDetailTableModel;
import edu.utn.gestion.ui.util.PopUpFactory;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.List;

/**
 * Created by martin on 27/02/16.
 */
public abstract class AbstractOrderDialog extends GenericDialog {
    protected OrderController controller;
    protected OrderDetailTableModel model;
    protected Order currentOrder;

    protected JScrollPane scrollPane;
    protected JTable tblOrderDetails;

    /**
     * Class constructor.
     *
     * @param parent
     * @param windowTitle
     * @param controller
     * @param order
     */
    public AbstractOrderDialog(JDialog parent, String windowTitle, OrderController controller, Order order) {
        super(parent, windowTitle, true);
        this.controller = controller;
        this.currentOrder = order;

        this.initModel();
    }

    @Override
    protected void initModel() {
        List<OrderDetail> orderDetailList = null;

        if (this.currentOrder != null) {
            orderDetailList = this.currentOrder.getOrderDetails();
        } else {
            try {
                orderDetailList = this.controller.getBooksForNewOrder();
            } catch (GestionAppException ex) {
                PopUpFactory.showErrorMessage(this, ex.getMessage());
            }
        }

        this.model.setObjectList(orderDetailList);
    }
}
