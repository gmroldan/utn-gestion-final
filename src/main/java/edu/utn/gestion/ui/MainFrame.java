package edu.utn.gestion.ui;

import edu.utn.gestion.ui.controller.OrderController;
import edu.utn.gestion.ui.dialog.book.BooksManagementDialog;
import edu.utn.gestion.ui.dialog.customer.CustomersManagementDialog;
import edu.utn.gestion.ui.dialog.employee.EmployeesManagementDialog;
import edu.utn.gestion.ui.dialog.order.NewOrderDialog;
import edu.utn.gestion.ui.dialog.supplier.SuppliersManagementDialog;
import edu.utn.gestion.ui.internal.NewSaleInternalFrame;
import edu.utn.gestion.ui.util.InternalFrameManager;
import org.apache.log4j.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

/**
 *
 * @author gerardo
 */
public class MainFrame extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(MainFrame.class);
    private static final String LOOK_AND_FEEL_DEFAULT_VALUE = "Nimbus";
    private static final String WINDOW_TITLE = "GestionApp";
    private static final MainFrame INSTANCE = new MainFrame();

    private JMenu menuFile;
    private JMenu menuEdit;
    private JMenu menuOrders;
    private JMenu menuHelp;
    private JMenu menuSales;
    private JMenuBar menuBarGestionApp;
    private JMenuItem menuItemAbout;
    private JMenuItem menuItemBooks;
    private JMenuItem menuItemCustomers;
    private JMenuItem menuItemEmployees;
    private JMenuItem menuItemSuppliers;
    private JMenuItem menuItemNewOrder;
    private JMenuItem menuItemExit;
    private JMenuItem menuItemNewSale;
    private JDesktopPane desktopPane;

    /**
    * Creates new form MainFrame
    */
    private MainFrame() {
        this.initLookAndFeel();
        this.initComponents();        
    }
    
    public static MainFrame getInstance() {
        return INSTANCE;
    }

    /**
    * This method is called from within the constructor to initialize the form.    *
    */
    private void initComponents() {
        this.menuBarGestionApp = new JMenuBar();
        this.menuFile = new JMenu("File");
        this.menuItemExit = new JMenuItem("Exit");
        this.menuEdit = new JMenu("Edit");
        this.menuOrders = new JMenu("Orders");
        this.menuSales = new JMenu("Sales");
        this.menuItemNewSale = new JMenuItem("New Sale");
        this.menuItemBooks = new JMenuItem("Books");
        this.menuItemCustomers = new JMenuItem("Customers");
        this.menuItemEmployees = new JMenuItem("Employees");
        this.menuItemSuppliers = new JMenuItem("Suppliers");
        this.menuItemNewOrder = new JMenuItem("New Order");
        this.menuHelp = new JMenu("Help");
        this.menuItemAbout = new JMenuItem("About");
        this.desktopPane = InternalFrameManager.getDesktopPane();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(WINDOW_TITLE);

        this.menuFile.add(this.menuItemExit);
        this.menuSales.add(this.menuItemNewSale);
        this.menuEdit.add(this.menuItemBooks);
        this.menuEdit.add(this.menuItemCustomers);
        this.menuEdit.add(this.menuItemEmployees);
        this.menuEdit.add(this.menuItemSuppliers);
        this.menuEdit.add(this.menuOrders);
        this.menuHelp.add(this.menuItemAbout);
        this.menuOrders.add(this.menuItemNewOrder);

        this.menuBarGestionApp.add(this.menuFile);
        this.menuBarGestionApp.add(this.menuSales);
        this.menuBarGestionApp.add(this.menuEdit);
        this.menuBarGestionApp.add(this.menuHelp);

        this.setJMenuBar(this.menuBarGestionApp);
        this.setContentPane(this.desktopPane);


        this.menuItemNewSale.addActionListener(event -> this.menuItemNewSaleActionPerformed(event));
        this.menuItemBooks.addActionListener(event -> this.menuItemBooksActionPerformed(event));
        this.menuItemCustomers.addActionListener(event -> this.menuItemCustomersActionPerformed(event));
        this.menuItemEmployees.addActionListener(event -> this.menuItemEmployeesActionPerformed(event));
        this.menuItemSuppliers.addActionListener(event -> this.menuItemSuppliersActionPerformed(event));
        this.menuItemNewOrder.addActionListener(event -> this.menuItemNewOrderActionPerformed(event));


        this.setSize(new Dimension(1000, 800));
        this.setLocationRelativeTo(null);
    }

    private void menuItemNewOrderActionPerformed(ActionEvent event) {
        new NewOrderDialog(this, true, new OrderController()).setVisible(true);
    }

    private void menuItemNewSaleActionPerformed(ActionEvent event) {
        InternalFrameManager.addInternalFrame(new NewSaleInternalFrame());
    }

    private void menuItemBooksActionPerformed(ActionEvent event) {
        new BooksManagementDialog(this, true).setVisible(true);
    }

    private void menuItemCustomersActionPerformed(ActionEvent event) {
        new CustomersManagementDialog(this, true).setVisible(true);
    }

    private void menuItemEmployeesActionPerformed(ActionEvent event) {
        new EmployeesManagementDialog(this, true).setVisible(true);
    }

    private void menuItemSuppliersActionPerformed(ActionEvent event) {
        new SuppliersManagementDialog(this, true).setVisible(true);
    }

    /**
    *  Executes the UI.
    *
    * @param args
    */
    public void execute(String args[]) {
        this.main(args);
    }

    /**
    * @param args the command line arguments
    */
    private void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                INSTANCE.setVisible(true);
            }
        });
    }

    /**
    *  Sets the default look and feel for the application.
    */
    private void initLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (LOOK_AND_FEEL_DEFAULT_VALUE.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
