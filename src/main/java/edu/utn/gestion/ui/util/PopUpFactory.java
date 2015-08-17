package edu.utn.gestion.ui.util;

import java.awt.Component;
import javax.swing.JOptionPane;

public class PopUpFactory {
    
    public static final void showErrorMessage(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, null, JOptionPane.ERROR_MESSAGE);
    }
}
