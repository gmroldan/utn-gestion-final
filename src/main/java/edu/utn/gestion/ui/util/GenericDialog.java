package edu.utn.gestion.ui.util;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by martin on 05/12/15.
 */
public abstract class GenericDialog extends JDialog {
    protected JPanel panel;
    protected JButton btnAccept;
    protected JButton btnCancel;

    public GenericDialog(JDialog parent, boolean modal) {
        super(parent, modal);
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
        this.btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                btnCancelActionPerformed(event);
            }
        });

        this.btnAccept = new JButton("Accept");
        this.btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                btnAcceptActionPerformed(event);
            }
        });

        this.initComponents();
    }

    protected abstract void formWindowOpened(WindowEvent event);
    protected abstract void initComponents();
    protected abstract void createMainPanel();
    protected abstract void initModel();
    protected abstract void btnAcceptActionPerformed(ActionEvent event);
    protected abstract void btnCancelActionPerformed(ActionEvent event);
}
