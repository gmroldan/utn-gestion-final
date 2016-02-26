package edu.utn.gestion.ui.dialog.order;

import edu.utn.gestion.model.Order;
import edu.utn.gestion.model.OrderStatusEnum;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

/**
 * Created by martin on 25/02/16.
 */
public class OrderTableModel extends GenericTableModel<Order> {
    private static final String[] COLUMN_NAMES = {"Id", "Status"};

    /**
     * Class constructor.
     */
    public OrderTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Long.class;
            case 1: return OrderStatusEnum.class;
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = this.objectList.get(rowIndex);

        switch (columnIndex) {
            case 0: return order.getId();
            case 1: return order.getStatus();
            default: return null;
        }
    }
}
