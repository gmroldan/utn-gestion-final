package edu.utn.gestion.ui.dialog.generic;

import edu.utn.gestion.exception.GestionAppException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by martin on 05/12/15.
 */
public abstract class GenericDialog extends JDialog {
    protected JPanel formPanel;
    protected JPanel buttonPanel;
    protected JButton btnAccept;
    protected JButton btnCancel;

    /**
     * Class constructor.
     *
     * @param parent
     * @param windowTitle
     * @param modal
     * @param windowTitle
     */
    public GenericDialog(JDialog parent, String windowTitle, boolean modal) {
        super(parent, windowTitle, modal);
        this.init();
    }

    private void init() {
        this.initGUIComponents();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent event) {
                formWindowOpened(event);
            }
        });

        this.setLocationRelativeTo(this.getParent());
    }

    /**
     * Initializes the default GUI components.
     */
    private void initGUIComponents() {
        this.btnCancel = new JButton("Cancel");
        this.btnCancel.addActionListener(event -> btnCancelActionPerformed(event));

        this.btnAccept = new JButton("Accept");
        this.btnAccept.addActionListener(event -> btnAcceptActionPerformed(event));

        this.buttonPanel = new JPanel();
        this.buttonPanel.add(this.btnAccept);
        this.buttonPanel.add(this.btnCancel);

        this.initComponents();

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(this.formPanel);
        this.formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(this.buttonPanel);
        this.buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.pack();
    }

    protected abstract void formWindowOpened(WindowEvent event);
    protected abstract void initComponents();
    protected abstract void createFormPanel();
    protected abstract void initModel();
    protected abstract void setObjectData() throws GestionAppException;
    protected abstract void btnAcceptActionPerformed(ActionEvent event);
    protected abstract void btnCancelActionPerformed(ActionEvent event);
}
