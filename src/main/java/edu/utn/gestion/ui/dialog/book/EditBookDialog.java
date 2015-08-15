package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
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
            this.controller.updateBook(this.currentBook);
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
