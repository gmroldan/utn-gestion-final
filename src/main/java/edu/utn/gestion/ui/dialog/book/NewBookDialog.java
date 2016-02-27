package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.ui.controller.BookController;
import edu.utn.gestion.ui.util.PopUpFactory;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

public class NewBookDialog extends AbstractBookDialog {
    private static final String WINDOW_TITLE = "New Book";

    /**
     * Class constructor.
     *
     * @param parent
     * @param modal
     * @param controller
     */
    public NewBookDialog(JDialog parent, boolean modal, BookController controller) {
        super(parent, WINDOW_TITLE, modal, controller, null);
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        try {
            this.setObjectData();
            this.controller.save(this.currentBook);
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
