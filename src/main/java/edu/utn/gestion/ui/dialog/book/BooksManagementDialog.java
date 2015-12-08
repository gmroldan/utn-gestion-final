package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.model.Book;
import edu.utn.gestion.ui.controller.BookController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;
import java.awt.Frame;

public class BooksManagementDialog extends GenericManagementDialog<Book, String> {

    public BooksManagementDialog(Frame parent, boolean modal) {
        super(parent, modal, new BookController(), new BookTableModel());
        this.setTitle("Books Management");
    }
    
    @Override
    protected void showNewObjectDialog() {
        new NewBookDialog(this, true, (BookController) this.controller).setVisible(true);
    }

    @Override
    protected void showEditObjectDialog(Book book) {
        new EditBookDialog(this, true, (BookController) this.controller, book).setVisible(true);
    }
}
