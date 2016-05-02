package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.model.Book;
import edu.utn.gestion.ui.controller.BookController;
import edu.utn.gestion.ui.controller.generic.GenericController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;
import java.awt.Frame;

public class BooksManagementDialog extends GenericManagementDialog<Book, String> {
    private static final String WINDOW_TITLE = "Books Management";
    private final BookController controller;

    /**
     * Class constructor.
     *
     * @param parent
     * @param modal
     */
    public BooksManagementDialog(Frame parent, boolean modal) {
        super(parent, WINDOW_TITLE, modal, new BookTableModel());
        this.controller = new BookController();
    }
    
    @Override
    protected void showNewObjectDialog() {
        new NewBookDialog(this, true, this.controller).setVisible(true);
    }

    @Override
    protected void showEditObjectDialog(Book book) {
        new EditBookDialog(this, true, this.controller, book).setVisible(true);
    }

    @Override
    protected GenericController<Book, String> getController() {
        return this.controller;
    }
}
