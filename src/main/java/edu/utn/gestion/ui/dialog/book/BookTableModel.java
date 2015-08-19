package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.model.Book;
import edu.utn.gestion.ui.util.GenericTableModel;

public class BookTableModel extends GenericTableModel<Book> {
    private static final String[] COLUMN_NAMES = {"ISBN", "Title", "Author", "Price", "Stock"};

    public BookTableModel() {
        super(COLUMN_NAMES);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 1: return String.class;
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
            case 3: return book.getPrice();
            case 4: return book.getStock();
            default: return null;
        }        
    }        
}
