package edu.utn.gestion.ui.util;

import javax.swing.JOptionPane;
import java.awt.Component;

public class PopUpFactory {
    
    public static final void showErrorMessage(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, null, JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, null, JOptionPane.INFORMATION_MESSAGE);
    }
}
