package edu.utn.gestion.ui.internal;

import edu.utn.gestion.model.Employee;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

/**
 * Created by Benja on 02/08/2016.
 */
public class EmployeeForSettlementTableModel extends GenericTableModel<Employee> {
    private static final String[] COLUMN_NAMES = {"Empleado", "Liquidar"};

    public EmployeeForSettlementTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return Boolean.class;
            default: return Object.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = this.objectList.get(rowIndex);

        switch (columnIndex) {
            case 0: return employee.getName();
            case 1: return true;
            default: return null;
        }
    }
}
