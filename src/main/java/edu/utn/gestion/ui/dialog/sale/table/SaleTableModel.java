package edu.utn.gestion.ui.dialog.sale.table;

import edu.utn.gestion.model.Sale;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

import java.util.Date;

/**
 * Created by martin on 07/08/16.
 */
public class SaleTableModel extends GenericTableModel<Sale> {
    private static final String[] COLUMN_NAMES = {"Id", "Date", "Customer", "Books Sold", "Total ($)"};

    /**
     * Class constructor.
     */
    public SaleTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Long.class;
            case 1: return Date.class;
            case 2: return String.class;
            case 3: return Integer.class;
            case 4: return Double.class;
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sale sale = this.objectList.get(rowIndex);

        switch (columnIndex) {
            case 0: return sale.getId();
            case 1: return sale.getDate();
            case 2: return sale.getCustomer().getName();
            case 3: return sale.getSaleDetails().size();
            case 4: return sale.getTotalAmount();
            default: return null;
        }
    }
}
