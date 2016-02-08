package edu.utn.gestion.ui.dialog.order;

import edu.utn.gestion.model.OrderDetail;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

/**
 * Created by martin on 07/02/16.
 */
public class OrderDetailTableModel extends GenericTableModel<OrderDetail> {
    private static final String[] COLUMN_NAMES = {"ISBN", "Title", "Quantity"};

    public OrderDetailTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Object.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderDetail orderDetail = this.objectList.get(rowIndex);

        switch (columnIndex) {
            case 0: return orderDetail.getBook().getIsbn();
            case 1: return orderDetail.getBook().getTitle();
            case 2: return orderDetail.getQuantity();
            default: return null;
        }
    }
}
