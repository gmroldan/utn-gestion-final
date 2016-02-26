package edu.utn.gestion.ui.dialog.order;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.ui.controller.OrderController;
import edu.utn.gestion.ui.util.PopUpFactory;
import org.apache.commons.collections4.CollectionUtils;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * Created by martin on 05/02/16.
 */
public class NewOrderDialog extends JDialog {
    private static final String WINDOW_TITLE = "Send Order";
    private OrderController controller;
    private OrderDetailTableModel model;
    protected JButton btnNew;
    protected JScrollPane jScrollPane1;
    protected JTable tableBooksForOrder;

    /**
     * Creates new form NewOrderDialog
     *
     * @param parent
     * @param modal
     * @parm model
     */
    public NewOrderDialog(Frame parent, boolean modal, OrderController controller) {
        super(parent, modal);
        this.init(controller);
    }

    /**
     * Creates new form NewOrderDialog
     *
     * @param parent
     * @param modal
     * @parm model
     */
    public NewOrderDialog(Dialog parent, boolean modal, OrderController controller) {
        super(parent, modal);
        this.init(controller);
    }

    private void init(OrderController controller) {
        this.controller = controller;
        this.model = new OrderDetailTableModel();
        this.initComponents();
        this.setTitle(WINDOW_TITLE);
        this.setLocationRelativeTo(this.getParent());
    }

    /**
        * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.tableBooksForOrder = new JTable(this.model);
        this.btnNew = new JButton("New Order");

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        this.addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(WindowEvent event) {
                formWindowGainedFocus(event);
            }
            public void windowLostFocus(WindowEvent event) {
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent event) {
                formWindowOpened(event);
            }
        });

        this.jScrollPane1.setViewportView(this.tableBooksForOrder);

        this.btnNew.addActionListener(event -> this.btnNewActionPerformed());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.jScrollPane1, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(this.btnNew)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.btnNew))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.jScrollPane1, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnNewActionPerformed() {
        try {
            long orderId = this.controller.save(this.model.getObjectList());
            PopUpFactory.showInfoMessage(this, "A new order has been created successfully. Order Id: " + orderId);
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
        } finally {
            this.updateObjectList();
        }
    }

    private void formWindowGainedFocus(WindowEvent event) {
        this.updateObjectList();
    }

    private void formWindowOpened(WindowEvent event) {
        this.updateObjectList();
    }

    private void updateObjectList() {
        try {
            this.model.setObjectList(this.controller.getBooksForNewOrder());

            if (CollectionUtils.isEmpty(this.model.getObjectList())) {
                this.btnNew.setEnabled(false);
            } else {
                this.btnNew.setEnabled(true);
            }
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
            this.dispose();
        }
    }
}
