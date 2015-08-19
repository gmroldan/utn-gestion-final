package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.model.Book;
import edu.utn.gestion.ui.controller.BookController;
import edu.utn.gestion.ui.util.GenericManagementDialog;
import java.awt.Frame;

public class BooksManagementDialog extends GenericManagementDialog<Book, String> {
    private final BookController bookController;
    private final BookTableModel bookTableModel;

    public BooksManagementDialog(Frame parent, boolean modal) {
        super(parent, modal, BookController.getInstance(), new BookTableModel());
        this.bookController = (BookController) this.controller;
        this.bookTableModel = (BookTableModel) this.model;
    }
    
    @Override
    protected void showNewObjectDialog() {
        new NewBookDialog(this, true).setVisible(true);
    }

    @Override
    protected void showEditObjectDialog(Book book) {
        new EditBookDialog(this, true, book).setVisible(true);
    }
}
