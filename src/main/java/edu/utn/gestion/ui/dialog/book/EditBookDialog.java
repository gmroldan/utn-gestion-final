package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.ui.util.PopUpFactory;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

public class EditBookDialog extends AbstractBookDialog {

    public EditBookDialog(JDialog parent, boolean modal, Book book) {
        super(parent, modal, book);
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        try {
            this.setBookData();
            this.controller.update(this.currentBook);
            this.dispose();
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
        }
    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {
        this.dispose();
    }
}
