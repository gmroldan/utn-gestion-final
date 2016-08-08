package edu.utn.gestion.ui.dialog.sale;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Sale;
import edu.utn.gestion.model.User;
import edu.utn.gestion.ui.constants.MonthEnum;
import edu.utn.gestion.ui.controller.SaleController;
import edu.utn.gestion.ui.controller.generic.GenericController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;
import edu.utn.gestion.ui.dialog.sale.table.SaleTableModel;
import edu.utn.gestion.ui.util.PopUpFactory;
import edu.utn.gestion.ui.util.Session;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;
import java.awt.event.ItemEvent;
import java.util.List;

/**
 * Created by martin on 07/08/16.
 */
public class MySalesDialog extends GenericManagementDialog<Sale, Long> {
    private static final String WINDOW_TITLE = "My Sales";
    private static final Integer[] yearArray = {2016, 2015, 2014, 2013, 2012, 2011, 2010};

    protected JComboBox cmbMonths;
    protected JComboBox cmbYears;

    protected final SaleController controller;

    /**
     * Class constructor.
     *
     * @param parent
     */
    public MySalesDialog(JFrame parent) {
        super(parent, WINDOW_TITLE, true, new SaleTableModel());
        this.controller =  new SaleController();
        this.changeUIComponents();
    }

    /**
     * Class constructor.
     *
     * @param parent
     * @param title
     */
    public MySalesDialog(JFrame parent, String title, GenericTableModel tableModel) {
        super(parent, title, true, tableModel);
        this.controller =  new SaleController();
        this.changeUIComponents();
    }

    private void changeUIComponents() {
        this.btnNew.setVisible(false);
        this.btnDelete.setVisible(false);
        this.txtSearch.setVisible(false);
        this.btnSearch.setVisible(false);

        this.cmbMonths = new JComboBox(new DefaultComboBoxModel(MonthEnum.values()));
        this.cmbYears = new JComboBox(yearArray);

        GroupLayout searchPanelLayout = new GroupLayout(this.searchPanel);
        this.searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
                searchPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(searchPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(this.cmbMonths)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.cmbYears)
                                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
                searchPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(searchPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(searchPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(this.cmbMonths, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(this.cmbYears))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        this.cmbMonths.addActionListener(event -> this.cmbMonthsActionPerformed());
        this.cmbYears.addActionListener(event -> this.cmbYearsActionPerformed());
    }

    private void cmbMonthsActionPerformed() {
        this.search();
    }

    private void cmbYearsActionPerformed() {
        this.search();
    }

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


    @Override
    protected void updateObjectList() {
        this.search();
    }

    protected void setObjectList(List<Sale> saleList) {
        this.model.setObjectList(saleList);
    }

    @Override
    protected void checkBoxChanged(ItemEvent event) {
        // TODO: Add implementation...
    }

    @Override
    protected void showEditObjectDialog(Sale sale) {
        // TODO: Add implementation...
    }

    @Override
    protected void showNewObjectDialog() {
        // TODO: Add implementation...
    }

    @Override
    protected GenericController<Sale, Long> getController() {
        return this.controller;
    }
}
