package edu.utn.gestion.ui.internal;

import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.SaleDetail;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

/**
 * Created by martin on 08/12/15.
 */
public class SaleDetailTableModel extends GenericTableModel<SaleDetail> {
    private static final String[] COLUMN_NAMES = {"Title", "Price", "Quantity", "Amount"};

    public SaleDetailTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return Double.class;
            case 2: return Integer.class;
            case 3: return Double.class;
            default: return Object.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SaleDetail saleDetail = this.objectList.get(rowIndex);
        Book book = saleDetail.getBook();

        switch (columnIndex) {
            case 0: return book.getTitle();
            case 1: return book.getPrice();
            case 2: return saleDetail.getQuantity();
            case 3: return saleDetail.getAmount();
            default: return null;
        }
    }
}
