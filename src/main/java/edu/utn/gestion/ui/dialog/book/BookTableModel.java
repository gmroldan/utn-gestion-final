package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.model.Book;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

public class BookTableModel extends GenericTableModel<Book> {
    private static final String[] COLUMN_NAMES = {"ISBN", "Title", "Author", "Editorial", "Price", "Stock"};

    public BookTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return Double.class;
            case 5: return Integer.class;
            default: return Object.class;
        }        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = this.objectList.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return book.getIsbn();
            case 1: return book.getTitle();
            case 2: return book.getAuthor();
            case 3: return book.getEditorial();
            case 4: return book.getPrice();
            case 5: return book.getCurrentStock();
            default: return null;
        }        
    }        
}
