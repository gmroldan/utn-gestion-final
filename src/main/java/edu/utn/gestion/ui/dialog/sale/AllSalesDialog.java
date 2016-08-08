package edu.utn.gestion.ui.dialog.sale;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Sale;
import edu.utn.gestion.model.User;
import edu.utn.gestion.ui.constants.MonthEnum;
import edu.utn.gestion.ui.dialog.sale.table.AllSalesTable;
import edu.utn.gestion.ui.util.PopUpFactory;
import edu.utn.gestion.ui.util.Session;

import javax.swing.JFrame;
import java.util.List;

/**
 * Created by martin on 08/08/16.
 */
public class AllSalesDialog extends MySalesDialog {
    private static final String WINDOW_TITLE = "Sales";

    /**
     * Class constructor.
     *
     * @param parent
     */
    public AllSalesDialog(JFrame parent) {
        super(parent, WINDOW_TITLE, new AllSalesTable());
    }

    @Override
    protected void search() {
        int month = ((MonthEnum) this.cmbMonths.getSelectedItem()).getNumVal();
        int year = (Integer) this.cmbYears.getSelectedItem();

        User user = Session.getCurrentUser();
        List<Sale> saleList = null;

        try {
            saleList = this.controller.searchByPeriod(month, year);
        } catch (GestionAppException e) {
            PopUpFactory.showErrorMessage(this, e.getMessage());
        }

        this.setObjectList(saleList);
    }
}
