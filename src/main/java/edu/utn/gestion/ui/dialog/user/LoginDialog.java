package edu.utn.gestion.ui.dialog.user;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.User;
import edu.utn.gestion.ui.MainFrame;
import edu.utn.gestion.ui.controller.UserController;
import edu.utn.gestion.ui.util.PopUpFactory;
import edu.utn.gestion.ui.util.Session;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Created by martin on 27/07/16.
 */
public class LoginDialog extends JDialog {
    private final UserController controller = new UserController();

    private JPanel panel;
    private JTextField txtUserName;
    private JPasswordField txtPassword;
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
        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.lblUserName = new JLabel("User");
        this.lblUserName.setBounds(10, 10, 80, 25);
        this.panel.add(this.lblUserName);

        this.txtUserName = new JTextField(20);
        this.txtUserName.setBounds(100, 10, 160, 25);
        this.panel.add(this.txtUserName);

        this.lblPassword = new JLabel("Password");
        this.lblPassword.setBounds(10, 40, 80, 25);
        this.panel.add(this.lblPassword);

        this.txtPassword = new JPasswordField(20);
        this.txtPassword.setBounds(100, 40, 160, 25);
        this.panel.add(this.txtPassword);

        this.btnLogin = new JButton("Login");
        this.btnLogin.setBounds(10, 80, 80, 25);
        this.panel.add(this.btnLogin);

        this.btnCancel = new JButton("Cancel");
        this.btnCancel.setBounds(180, 80, 80, 25);
        this.panel.add(this.btnCancel);

        this.btnLogin.addActionListener(event -> this.btnLoginActionPerformed());
        this.btnCancel.addActionListener(event -> this.btnCancelActionPerformed());

        this.setSize(300, 150);
        this.add(this.panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void btnLoginActionPerformed() {
        String userName = this.txtUserName.getText();
        String password = this.txtPassword.getText();
        try {
            User user = this.controller.login(userName, password);
            Session.init(user);
            this.dispose();
        } catch (GestionAppException e) {
            PopUpFactory.showErrorMessage(this, e.getMessage());
        }
    }

    private void btnCancelActionPerformed() {
        System.exit(0);
    }

}
