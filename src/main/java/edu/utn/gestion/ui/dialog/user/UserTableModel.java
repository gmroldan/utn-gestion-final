package edu.utn.gestion.ui.dialog.user;

import edu.utn.gestion.model.EmployeeCategoryEnum;
import edu.utn.gestion.model.GenderEnum;
import edu.utn.gestion.model.User;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

/**
 * Created by martin on 26/07/16.
 */
public class UserTableModel extends GenericTableModel<User> {
    private static final String[] COLUMN_NAMES = {"Id", "Name"};

    /**
     * Class constructor.
     */
    public UserTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Long.class;
            case 1: return String.class;
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = this.objectList.get(rowIndex);

        switch (columnIndex) {
            case 0: return user.getId();
            case 1: return user.getName();
            default: return null;
        }
    }
}
