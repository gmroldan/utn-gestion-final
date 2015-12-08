package edu.utn.gestion.ui.dialog.employee;

import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.GenderEnum;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

/**
 * Created by martin on 08/12/15.
 */
public class EmployeeTableModel extends GenericTableModel<Employee> {
    private static final String[] COLUMN_NAMES = {"Id", "Name", "CUIT", "Phone", "Email", "Gender"};

    public EmployeeTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Long.class;
            case 5: return GenderEnum.class;
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = this.objectList.get(rowIndex);

        switch (columnIndex) {
            case 0: return employee.getId();
            case 1: return employee.getName();
            case 2: return employee.getCuit();
            case 3: return employee.getPhoneNumber();
            case 4: return employee.getEmail();
            case 5: return employee.getGender();
            default: return null;
        }
    }
}
