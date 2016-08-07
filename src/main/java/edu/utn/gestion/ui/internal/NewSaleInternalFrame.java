package edu.utn.gestion.ui.internal;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Customer;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Sale;
import edu.utn.gestion.model.SaleDetail;
import edu.utn.gestion.ui.constants.UIConstants;
import edu.utn.gestion.ui.controller.BookController;
import edu.utn.gestion.ui.controller.CustomerController;
import edu.utn.gestion.ui.controller.EmployeeController;
import edu.utn.gestion.ui.controller.SaleController;
import edu.utn.gestion.ui.util.FormUtils;
import edu.utn.gestion.ui.util.IconFactory;
import edu.utn.gestion.ui.util.InternalFrameManager;
import edu.utn.gestion.ui.util.PopUpFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.awt.GridBagLayout;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * Created by martin on 08/12/15.
 */
public class NewSaleInternalFrame extends JInternalFrame {
    private static final String WINDOW_TITLE = "New Sale";
    private JButton btnAddSaleDetail;
    private JButton btnCancelSale;
    private JButton btnSaveSale;
    private JComboBox cmbBooks;
    private JComboBox cmbCustomers;
    private JComboBox cmbEmployees;
    private JLabel lblTotal;
    private JLabel lblCustomer;
    private JLabel lblEmployee;
    private JLabel lblBook;
    private JPanel panelTop;
    private JPanel panelSaleDetail;
    private JScrollPane jScrollPane1;
    private JTable tblSaleDetail;
    private SaleDetailTableModel tableModel;
    private JTextField txtSaleQuantity;
    private JTextField txtSaleTotal;

    private SaleController controller;
    private Sale currentSale;

    public NewSaleInternalFrame() {
        super();
        this.initComponents();
        this.initModel();
        this.controller = new SaleController();
        this.currentSale = new Sale();
        this.updateObjectList();
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        this.createTopPanel();

        this.panelSaleDetail = new JPanel();
        this.lblBook = new JLabel("Book");
        this.cmbBooks = new JComboBox();
        this.jScrollPane1 = new JScrollPane();
        this.tblSaleDetail = new JTable();
        this.tableModel = new SaleDetailTableModel();
        this.btnAddSaleDetail = new JButton("Add"
                , IconFactory.getIcon(UIConstants.ICON_EDIT_ADD_LOCATION));
        this.lblTotal = new JLabel("Total ($):");
        this.txtSaleTotal = new JTextField(10);
        this.txtSaleQuantity = new JTextField();
        this.btnCancelSale = new JButton("Cancel"
                , IconFactory.getIcon(UIConstants.ICON_BUTTON_CANCEL_LOCATION));
        this.btnSaveSale = new JButton("Save"
                , IconFactory.getIcon(UIConstants.ICON_BUTTON_ACCEPT_LOCATION));

        this.tblSaleDetail.setModel(this.tableModel);
        this.jScrollPane1.setViewportView(tblSaleDetail);

        this.txtSaleTotal.setEditable(false);

        GroupLayout jPanel3Layout = new GroupLayout(this.panelSaleDetail);
        this.panelSaleDetail.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(this.jScrollPane1)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(this.lblBook)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.cmbBooks)
                                .addGap(18, 18, 18)
                                .addComponent(this.txtSaleQuantity, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(this.btnAddSaleDetail)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.lblTotal)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(this.txtSaleTotal)))
        );

        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.btnAddSaleDetail)
                                                .addComponent(this.txtSaleQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(this.lblBook)
                                        .addComponent(this.cmbBooks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.jScrollPane1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(this.txtSaleTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(this.lblTotal))
                                .addContainerGap())
        );

        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent event) {
                super.internalFrameClosed(event);
                InternalFrameManager.removeInternalFrame(event.getInternalFrame());
            }
        });

        this.btnAddSaleDetail.addActionListener(event -> this.btnAddSaleDetailActionPerformed());
        this.btnCancelSale.addActionListener(event -> this.btnCancelSaleActionPerformed());
        this.btnSaveSale.addActionListener(event -> this.btnSaveSaleActionPerformed());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.panelSaleDetail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(this.panelTop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(this.btnSaveSale)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(this.btnCancelSale)))
                                .addContainerGap())
        );

        layout.linkSize(SwingConstants.HORIZONTAL, this.panelTop);

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.panelTop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(this.panelSaleDetail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(this.btnCancelSale, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.btnSaveSale, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        layout.linkSize(SwingConstants.VERTICAL, this.panelTop);

        this.pack();

        this.setTitle(WINDOW_TITLE);
        this.setClosable(true);
        this.setResizable(true);
        this.setLocation(0, 0);
        this.setSize(700, 500);
    }

    /**
     * Creates a simple panel to select the customer and the employee for the current sale.
     */
    private void createTopPanel() {
        this.panelTop = new JPanel(new GridBagLayout());

        this.lblCustomer = new JLabel("Customer");
        this.lblEmployee = new JLabel("Employee");

        this.cmbCustomers = new JComboBox();
        this.cmbEmployees = new JComboBox();

        FormUtils.addLabel(this.lblCustomer, this.panelTop);
        FormUtils.addLastField(this.cmbCustomers, this.panelTop);
        FormUtils.addLabel(this.lblEmployee, this.panelTop);
        FormUtils.addLastField(this.cmbEmployees, this.panelTop);
    }

    private void initModel() {
        List<Customer> customers = null;
        List<Employee> employees = null;
        List<Book> books = null;

        try {
            CustomerController customerController = new CustomerController();
            customers = customerController.findAll();

            EmployeeController employeeController = new EmployeeController();
            employees = employeeController.findAll();

            BookController bookController = new BookController();
            books = bookController.findAll();
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
            this.dispose();
        }

        if (CollectionUtils.isNotEmpty(customers)) {
            this.cmbCustomers.setModel(new DefaultComboBoxModel(customers.toArray()));
        }

        if (CollectionUtils.isNotEmpty(employees)) {
            this.cmbEmployees.setModel(new DefaultComboBoxModel(employees.toArray()));
        }

        if (CollectionUtils.isNotEmpty(books)) {
            this.cmbBooks.setModel(new DefaultComboBoxModel(books.toArray()));
        }
    }

    private void btnAddSaleDetailActionPerformed() {
        Book book = (Book) this.cmbBooks.getSelectedItem();

        String quantityText = this.txtSaleQuantity.getText();
        int quantity = 0;
        if (StringUtils.isNotEmpty(quantityText)) {
            quantity = Integer.valueOf(quantityText);
        }

        if (book != null && quantity > 0) {
            this.currentSale.addSaleDetail(book, quantity);
            this.txtSaleTotal.setText(String.valueOf(this.currentSale.getTotalAmount()));
        }

        this.updateObjectList();
    }

    private void btnSaveSaleActionPerformed() {
        try {
            Customer customer = (Customer) this.cmbCustomers.getSelectedItem();
            Employee employee = (Employee) this.cmbEmployees.getSelectedItem();

            this.currentSale.setCustomer(customer);
            this.currentSale.setEmployee(employee);
            this.currentSale.setDate(new Date());

            this.controller.save(this.currentSale);
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
        }
    }

    private void btnCancelSaleActionPerformed() {
        this.dispose();
    }

    private void updateObjectList() {
        List<SaleDetail> details = this.currentSale.getSaleDetails();
        this.tableModel.setObjectList(details);
    }
}
