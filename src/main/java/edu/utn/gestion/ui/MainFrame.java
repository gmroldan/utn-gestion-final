package edu.utn.gestion.ui;

import edu.utn.gestion.ui.constants.UIConstants;
import edu.utn.gestion.ui.dialog.book.BooksManagementDialog;
import edu.utn.gestion.ui.dialog.customer.CustomersManagementDialog;
import edu.utn.gestion.ui.dialog.employee.EmployeesManagementDialog;
import edu.utn.gestion.ui.dialog.order.OrdersManagementDialog;
import edu.utn.gestion.ui.dialog.settlement.AttendanceDialog;
import edu.utn.gestion.ui.dialog.settlement.SettlementDialog;
import edu.utn.gestion.ui.dialog.supplier.SuppliersManagementDialog;
import edu.utn.gestion.ui.dialog.user.LoginDialog;
import edu.utn.gestion.ui.dialog.user.UserManagementDialog;
import edu.utn.gestion.ui.internal.NewSaleInternalFrame;
import edu.utn.gestion.ui.util.IconFactory;
import edu.utn.gestion.ui.util.InternalFrameManager;
import edu.utn.gestion.ui.util.Session;
import org.apache.log4j.Logger;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author gerardo
 */
public class MainFrame extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(MainFrame.class);
    private static final String LOOK_AND_FEEL_DEFAULT_VALUE = "Nimbus";
    private static final String WINDOW_TITLE = "GestionApp - %s";
    private static final MainFrame INSTANCE = new MainFrame();

    private JMenu menuFile;
    private JMenu menuEdit;
    private JMenu menuHelp;
    private JMenu menuSales;
    private JMenu menuEmployees;
    private JMenu menuAdmin;
    private JMenuBar menuBarGestionApp;
    private JMenuItem menuItemAbout;
    private JMenuItem menuItemBooks;
    private JMenuItem menuItemCustomers;
    private JMenuItem menuItemSuppliers;
    private JMenuItem menuItemOrders;
    private JMenuItem menuItemExit;
    private JMenuItem menuItemNewSale;
    private JMenuItem menuItemEmployees;
    private JMenuItem menuItemAttendance;
    private JMenuItem menuItemSettlement;
    private JMenuItem menuItemAdminUsers;
    private JDesktopPane desktopPane;
    private JPanel statusPanel;

    /**
    * Creates new form MainFrame
    */
    private MainFrame() {
        this.initLookAndFeel();
        this.showLogin();
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
        this.menuItemExit = new JMenuItem("Exit"
                , IconFactory.getIcon(UIConstants.ICON_APP_EXIT_LOCATION));
        this.menuEdit = new JMenu("Edit");
        this.menuSales = new JMenu("Sales");
        this.menuEmployees = new JMenu("Employees");
        this.menuAdmin = new JMenu("Admin");

        this.menuItemNewSale = new JMenuItem("New Sale"
                , IconFactory.getIcon(UIConstants.ICON_APP_NEW_SALE_LOCATION));
        this.menuItemBooks = new JMenuItem("Books"
                , IconFactory.getIcon(UIConstants.ICON_APP_BOOKS_LOCATION));
        this.menuItemCustomers = new JMenuItem("Customers"
                , IconFactory.getIcon(UIConstants.ICON_APP_CUSTOMERS_LOCATION));
        this.menuItemEmployees = new JMenuItem("Employees"
                , IconFactory.getIcon(UIConstants.ICON_APP_EMPLOYEES_LOCATION));
        this.menuItemSuppliers = new JMenuItem("Suppliers"
                , IconFactory.getIcon(UIConstants.ICON_APP_SUPPLIERS_LOCATION));
        this.menuItemOrders = new JMenuItem("Orders"
                , IconFactory.getIcon(UIConstants.ICON_APP_ORDERS_LOCATION));
        this.menuItemAttendance = new JMenuItem("Attendance"
                , IconFactory.getIcon(UIConstants.ICON_APP_ATTENDANCE_LOCATION));
        this.menuItemSettlement = new JMenuItem("Settlement");
        this.menuHelp = new JMenu("Help");
        this.menuItemAbout = new JMenuItem("About"
                , IconFactory.getIcon(UIConstants.ICON_APP_ABOUT_LOCATION));
        this.menuItemAdminUsers = new JMenuItem("Users"
                , IconFactory.getIcon(UIConstants.ICON_APP_USERS_LOCATION));

        this.desktopPane = InternalFrameManager.getDesktopPane();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(String.format(WINDOW_TITLE, Session.toStringCurrentUser()));

        this.menuFile.add(this.menuItemExit);
        this.menuSales.add(this.menuItemNewSale);
        this.menuEdit.add(this.menuItemBooks);
        this.menuEdit.add(this.menuItemCustomers);
        this.menuEdit.add(this.menuItemSuppliers);
        this.menuEdit.add(this.menuItemOrders);
        this.menuHelp.add(this.menuItemAbout);
        this.menuEmployees.add(this.menuItemAttendance);
        this.menuEmployees.add(this.menuItemSettlement);
        this.menuEmployees.add(this.menuItemEmployees);
        this.menuAdmin.add(this.menuItemAdminUsers);

        this.menuBarGestionApp.add(this.menuFile);
        this.menuBarGestionApp.add(this.menuEdit);
        this.menuBarGestionApp.add(this.menuSales);
        this.menuBarGestionApp.add(this.menuEmployees);
        this.menuBarGestionApp.add(this.menuAdmin);
        this.menuBarGestionApp.add(this.menuHelp);

        this.setJMenuBar(this.menuBarGestionApp);

        this.menuItemExit.addActionListener(event -> this.menuItemExitActionPerformed());
        this.menuItemNewSale.addActionListener(event -> this.menuItemNewSaleActionPerformed());
        this.menuItemBooks.addActionListener(event -> this.menuItemBooksActionPerformed());
        this.menuItemCustomers.addActionListener(event -> this.menuItemCustomersActionPerformed());
        this.menuItemSuppliers.addActionListener(event -> this.menuItemSuppliersActionPerformed());
        this.menuItemOrders.addActionListener(event -> this.menuItemOrdersActionPerformed());
        this.menuItemEmployees.addActionListener(event -> this.menuItemEmployeesActionPerformed());
        this.menuItemAttendance.addActionListener(event -> this.menuItemAttendanceActionPerformed());
        this.menuItemSettlement.addActionListener(event -> this.menuItemSettlementActionPerformed());
        this.menuItemAdminUsers.addActionListener(event -> this.menuItemAdminUsersActionPerformed());

        this.setSize(new Dimension(1000, 800));
        this.setLayout(new BorderLayout());
        this.createStatusBar();
        this.add(this.desktopPane, BorderLayout.CENTER);
        this.add(this.statusPanel, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);

        this.disableComponentsForNonAdminUsers();
    }

    /**
     * Disables some UI components if the current user is not ADMIN.
     */
    private void disableComponentsForNonAdminUsers() {
        if (!Session.isCurrentUserAdmin()) {
            this.menuAdmin.setVisible(false);

            if (Session.isCurrentUserVendedor()) {
                this.menuEmployees.setVisible(false);
                this.menuEdit.setVisible(false);
            }

            if (Session.isCurrentUserAdministrativo()) {
                this.menuSales.setVisible(false);
            }
        }
    }

    /**
     * Creates a status bar that shows a welcome message and the
     * current date-time.
     */
    private void createStatusBar() {
        this.statusPanel = new JPanel();

        final JLabel lblDateTime = new JLabel();
        final JLabel lblWelcomeMessage
                = new JLabel("Universidad Tecnológica Nacional - Facultad Regional Tucumán", JLabel.LEFT);

        this.statusPanel.setLayout(new BorderLayout());
        this.statusPanel.setBorder(BorderFactory.createEtchedBorder());
        this.statusPanel.add(lblWelcomeMessage, BorderLayout.WEST);
        this.statusPanel.add(lblDateTime, BorderLayout.EAST);

        new Timer(1000, event -> {
            String dateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            lblDateTime.setText(dateTimeString);
        }).start();
    }

    private void showLogin() {
        new LoginDialog(this);
    }

    /**
     * Shows a confirm dialog to close the app.
     */
    private void menuItemExitActionPerformed() {
        int selectedOption
                = JOptionPane.showConfirmDialog(this,
                                                "Are you sure you want to exit?",
                                                null,
                                                JOptionPane.YES_NO_OPTION);

        if (selectedOption == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void menuItemOrdersActionPerformed() {
        new OrdersManagementDialog(this).setVisible(true);
    }

    private void menuItemNewSaleActionPerformed() {
        InternalFrameManager.addInternalFrame(new NewSaleInternalFrame());
    }

    private void menuItemBooksActionPerformed() {
        new BooksManagementDialog(this, true).setVisible(true);
    }

    private void menuItemCustomersActionPerformed() {
        new CustomersManagementDialog(this, true).setVisible(true);
    }

    private void menuItemEmployeesActionPerformed() {
        new EmployeesManagementDialog(this, true).setVisible(true);
    }

    private void menuItemSuppliersActionPerformed() {
        new SuppliersManagementDialog(this, true).setVisible(true);
    }

    private void menuItemAttendanceActionPerformed() {
        new AttendanceDialog(this, true).setVisible(true);
    }

    private void menuItemSettlementActionPerformed() {
        new SettlementDialog(this, true).setVisible(true);
    }

    private void menuItemAdminUsersActionPerformed() {
        new UserManagementDialog(this, true).setVisible(true);
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
        EventQueue.invokeLater(() -> INSTANCE.setVisible(true));
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
