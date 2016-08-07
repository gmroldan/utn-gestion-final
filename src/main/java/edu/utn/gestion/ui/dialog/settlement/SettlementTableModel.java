package edu.utn.gestion.ui.dialog.settlement;

import edu.utn.gestion.model.Settlement;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

/**
 * Created by ASUS on 05/08/2016.
 */
public class SettlementTableModel  extends GenericTableModel<Settlement> {
    private static final String[] COLUMN_NAMES = {"Periodo", "Categoría", "Bruto", "Descuento", "Neto"};

    public SettlementTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return String.class;
            default: return Object.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Settlement settlement = this.objectList.get(rowIndex);

        switch (columnIndex) {
            case 0: return settlement.getPeriod();
            case 1: return settlement.getCategory();
            case 2: return settlement.getRemunerationAmount();
            case 3: return settlement.getDiscount();
            case 4: return settlement.getNetPay();
            default: return null;
        }
    }
}