package edu.utn.gestion.ui.dialog.customer;

import edu.utn.gestion.model.Customer;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

public class CustomerTableModel extends GenericTableModel<Customer> {
    private static final String[] COLUMN_NAMES = {"Id", "Name", "CUIT", "Phone", "Email"};

    public CustomerTableModel() {
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
        Customer customer = this.objectList.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return customer.getId();
            case 1: return customer.getName();
            case 2: return customer.getCuit();
            case 3: return customer.getPhoneNumber();
            case 4: return customer.getEmail();
            default: return null;
        }
    }
    
}
