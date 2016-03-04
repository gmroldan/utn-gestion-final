package edu.utn.gestion.ui.dialog.order;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Order;
import edu.utn.gestion.ui.controller.OrderController;
import edu.utn.gestion.ui.dialog.order.table.OrderDetailTableModel;
import edu.utn.gestion.ui.util.FormUtils;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Created by martin on 27/02/16.
 */
public class ViewOrderDialog extends AbstractOrderDialog {
    private static final String WINDOW_TITLE = "Order Details";
    private JLabel lblId;
    private JLabel lblCreationDate;
    private JLabel lblStatus;
    private JTextField txtId;
    private JTextField txtCreationDate;
    private JTextField txtStatus;

    /**
     * Class constructor.
     *
     * @param parent
     * @param order
     * @param controller
     */
    public ViewOrderDialog(JDialog parent, OrderController controller, Order order) {
        super(parent, WINDOW_TITLE, controller, order);
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {
        this.btnCancel.setVisible(false);
        this.txtId.setText(String.valueOf(this.currentOrder.getId()));
        this.txtCreationDate.setText(String.valueOf(this.currentOrder.getCreationDate()));
        this.txtStatus.setText(String.valueOf(this.currentOrder.getStatus()));
    }

    @Override
    protected void initComponents() {
        this.lblId = new JLabel("Id");
        this.lblCreationDate = new JLabel("Creation Date");
        this.lblStatus = new JLabel("Status");
        this.txtId = new JTextField();
        this.txtCreationDate = new JTextField();
        this.txtStatus = new JTextField();
        this.tblOrderDetails = new JTable();
        this.scrollPane = new JScrollPane();
        this.model = new OrderDetailTableModel();

        this.lblId.setLabelFor(this.txtId);
        this.lblCreationDate.setLabelFor(this.txtCreationDate);
        this.lblStatus.setLabelFor(this.txtStatus);
        this.tblOrderDetails.setModel(this.model);
        this.scrollPane.setViewportView(this.tblOrderDetails);

        this.txtId.setEditable(false);
        this.txtCreationDate.setEditable(false);
        this.txtStatus.setEditable(false);

        this.createFormPanel();
    }

    @Override
    protected void createFormPanel() {
        this.formPanel = new JPanel();

        JPanel headerPanel = this.createHeaderPanel();

        GroupLayout layout = new GroupLayout(this.formPanel);
        this.formPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.scrollPane, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(headerPanel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(headerPanel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.scrollPane, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    /**
     * Creates a panel that displays the oder's info.
     *
     * @return
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new GridBagLayout());

        FormUtils.addLabel(this.lblId, headerPanel);
        FormUtils.addLastField(this.txtId, headerPanel);
        FormUtils.addLabel(this.lblCreationDate, headerPanel);
        FormUtils.addLastField(this.txtCreationDate, headerPanel);
        FormUtils.addLabel(this.lblStatus, headerPanel);
        FormUtils.addLastField(this.txtStatus, headerPanel);

        return headerPanel;
    }

    @Override
    protected void setObjectData() throws GestionAppException {
        // This method is not necessary.
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        this.dispose();
    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {
        // This method is not necessary.
    }
}
