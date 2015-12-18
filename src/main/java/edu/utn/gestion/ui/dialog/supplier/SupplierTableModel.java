package edu.utn.gestion.ui.dialog.supplier;

import edu.utn.gestion.model.Supplier;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

/**
 * Created by martin on 18/12/15.
 */
public class SupplierTableModel extends GenericTableModel<Supplier> {
    private static final String[] COLUMN_NAMES = {"Id", "Name", "Email"};

    /**
     * Creates a new instance of <code>SupplierTableModel</code>.
     */
    public SupplierTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Long.class;
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Supplier supplier = this.objectList.get(rowIndex);

        switch (columnIndex) {
            case 0: return supplier.getId();
            case 1: return supplier.getName();
            case 2: return supplier.getEmail();
            default: return null;
        }
    }
}
