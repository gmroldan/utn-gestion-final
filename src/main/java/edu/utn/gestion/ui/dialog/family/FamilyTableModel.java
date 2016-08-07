package edu.utn.gestion.ui.dialog.family;

import edu.utn.gestion.model.Family;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

import java.util.Date;

/**
 * Created by ASUS on 07/08/2016.
 */
public class FamilyTableModel extends GenericTableModel<Family> {

    private static final String[] COLUMN_NAMES = {"Name", "DNI", "Bound","Birth Date"};

    public FamilyTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Family family = this.objectList.get(rowIndex);

        switch (columnIndex) {
            case 0: return family.getName();
            case 1: return family.getDni();
            case 2: return family.getBound();
            case 3: return (family.getBirthDate().getDate()
                    + "-" + (family.getBirthDate().getMonth()+1)
                    + "-" + (family.getBirthDate().getYear()+1900));
            default: return null;
        }
    }
}
