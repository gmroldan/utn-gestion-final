package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.exception.GestionAppException;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

public class NewBookDialog extends AbstractBookDialog {

    public NewBookDialog(JDialog parent, boolean modal) {
        super(parent, modal, null);
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        try {
            this.setBookData();
            this.controller.saveBook(this.currentBook);
            this.dispose();
        } catch (GestionAppException ex) {
            this.showErrorMessage(ex.getMessage());
        }
    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {
        this.dispose();
    }
}
