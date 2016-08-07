package edu.utn.gestion.ui.dialog.user;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.User;
import edu.utn.gestion.ui.constants.UIConstants;
import edu.utn.gestion.ui.controller.UserController;
import edu.utn.gestion.ui.util.IconFactory;
import edu.utn.gestion.ui.util.PopUpFactory;
import edu.utn.gestion.ui.util.Session;
import org.apache.commons.lang3.StringUtils;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by martin on 27/07/16.
 */
public class LoginDialog extends JDialog {
    private final UserController controller = new UserController();

    private JPanel panel;
    private JPanel formPanel;
    private JPanel btnPanel;
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JLabel lblLogo;
    private JLabel lblUserName;
    private JLabel lblPassword;
    private JButton btnLogin;
    private JButton btnCancel;

    /**
     * Class constructor.
     */
    public LoginDialog(JFrame parent) {
        super(parent, "title", true);
        this.init();
    }

    /**
     * Initializes all the UI components.
     */
    private void init() {
        this.panel = new JPanel(new BorderLayout());

        this.formPanel = new JPanel();
        this.formPanel.setLayout(null);

        this.lblLogo = new JLabel(IconFactory.getIcon(UIConstants.SBS_LOGO_IMAGE));
        this.lblLogo.setBounds(50, 5, 220, 72);
        this.formPanel.add(this.lblLogo);

        this.lblUserName = new JLabel("User");
        this.lblUserName.setBounds(30, 80, 80, 25);
        this.formPanel.add(this.lblUserName);

        this.txtUserName = new JTextField(20);
        this.txtUserName.setBounds(100, 80, 160, 25);
        this.formPanel.add(this.txtUserName);

        this.lblPassword = new JLabel("Password");
        this.lblPassword.setBounds(30, 110, 80, 25);
        this.formPanel.add(this.lblPassword);

        this.txtPassword = new JPasswordField(20);
        this.txtPassword.setBounds(100, 110, 160, 25);
        this.formPanel.add(this.txtPassword);

        this.btnLogin = new JButton("Login");
        this.btnLogin.setBounds(30, 150, 80, 25);
        this.formPanel.add(this.btnLogin);

        this.btnCancel = new JButton("Cancel");
        this.btnCancel.setBounds(180, 150, 100, 25);
        this.formPanel.add(this.btnCancel);

        this.txtUserName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                loginKeyPressed(evt);
            }
        });

        this.txtPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                loginKeyPressed(evt);
            }
        });

        this.btnLogin.addActionListener(event -> this.btnLoginActionPerformed());
        this.btnCancel.addActionListener(event -> this.btnCancelActionPerformed());

        this.panel.add(this.formPanel);

        this.setSize(300, 200);
        this.setContentPane(this.panel);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.setVisible(true);
    }

    private void loginKeyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            this.btnLoginActionPerformed();
        }
    }

    private void btnLoginActionPerformed() {
        String userName = this.txtUserName.getText();
        String password = this.txtPassword.getText();

        if (this.validate(userName, password)) {
            try {
                User user = this.controller.login(userName, password);
                Session.init(user);
                this.dispose();
            } catch (GestionAppException e) {
                PopUpFactory.showErrorMessage(this, e.getMessage());
            }
        }
    }

    /**
     * Validates that userName and password are not empty values.
     * Displays a popup if some of the values are not valid.
     *
     * @param userName
     * @param password
     * @return
     */
    private boolean validate(final String userName, final String password) {
        if (StringUtils.isEmpty(userName)) {
            PopUpFactory.showInfoMessage(this, "User cannot be empty.");
        } else if (StringUtils.isEmpty(password)) {
            PopUpFactory.showInfoMessage(this, "Password cannot be empty.");
        } else {
            return true;
        }

        return false;
    }

    private void btnCancelActionPerformed() {
        System.exit(0);
    }
}
