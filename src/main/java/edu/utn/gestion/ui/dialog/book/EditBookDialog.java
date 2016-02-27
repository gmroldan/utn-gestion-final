package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.ui.controller.BookController;
import edu.utn.gestion.ui.util.PopUpFactory;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

public class EditBookDialog extends AbstractBookDialog {
    private static final String WINDOW_TITLE = "Edit Book";

    /**
     * Class constructor.
     *
     * @param parent
     * @param modal
     * @param controller
     * @param book
     */
    public EditBookDialog(JDialog parent, boolean modal, BookController controller, Book book) {
        super(parent, WINDOW_TITLE, modal, controller, book);
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        try {
            this.setObjectData();
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
