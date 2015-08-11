package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.model.Book;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

public class EditBookDialog extends AbstractBookDialog {

    public EditBookDialog(JDialog parent, boolean modal, Book book) {
        super(parent, modal, book);
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {
        this.dispose();
    }
}
