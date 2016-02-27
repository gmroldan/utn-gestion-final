package edu.utn.gestion.ui.dialog.order;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.ui.controller.OrderController;
import edu.utn.gestion.ui.dialog.order.table.OrderDetailTableModel;
import edu.utn.gestion.ui.util.PopUpFactory;
import org.apache.commons.collections4.CollectionUtils;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Created by martin on 05/02/16.
 */
public class NewOrderDialog extends AbstractOrderDialog {
    private static final String WINDOW_TITLE = "New Order";

    /**
     * Creates new form NewOrderDialog
     *
     * @param parent
     * @param modal
     * @parm model
     */
    public NewOrderDialog(JDialog parent, boolean modal, OrderController controller) {
        super(parent, WINDOW_TITLE, modal, null, controller);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @Override
    protected void initComponents() {
        this.model = new OrderDetailTableModel();
        this.tblOrderDetails = new JTable(this.model);

        this.scrollPane = new JScrollPane();
        this.scrollPane.setViewportView(this.tblOrderDetails);

        this.createFormPanel();
    }

    @Override
    protected void createFormPanel() {
        this.formPanel = new JPanel();

        GroupLayout layout = new GroupLayout(this.formPanel);
        this.formPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.scrollPane, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.scrollPane, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    @Override
    protected void setObjectData() throws GestionAppException {
        // TODO: to be implemented.
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        try {
            long orderId = this.controller.save(this.model.getObjectList());
            PopUpFactory.showInfoMessage(this, "A new order has been created successfully. Order Id: " + orderId);
            this.dispose();
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
        }
    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {
        this.dispose();
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {
        this.updateObjectList();
    }

    private void updateObjectList() {
        try {
            this.model.setObjectList(this.controller.getBooksForNewOrder());

            if (CollectionUtils.isEmpty(this.model.getObjectList())) {
                this.btnAccept.setEnabled(false);
            } else {
                this.btnAccept.setEnabled(true);
            }
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
            this.dispose();
        }
    }


}
