package edu.utn.gestion.service;

import edu.utn.gestion.dao.BookDAO;
import edu.utn.gestion.dao.CategoryDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import edu.utn.gestion.service.generic.GenericService;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 *
 * @author martin
 */
public class BookService extends GenericService<Book, String> {
    private static final BookService INSTANCE = new BookService();

    /**
     * Class constructor.
     */
    private BookService() {
        super(BookDAO.getInstance());
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
    
    @Override
    public List<Book> findBySearch(String searchString) throws GestionAppException {
        try {
            return this.genericDAO.findObjectsBySearch(searchString);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }

    public List<Book> findBooksWithMinStock() throws GestionAppException {
        try {
            return ((BookDAO) this.genericDAO).findBooksWithMinStock();
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
    
    public List<Category> findAllCategories() throws GestionAppException {
        try {
            return CategoryDAO.getInstance().findAll();
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }

    /**
     * Decreases the stock of a single book and then updates it into the DB.
     *
     * @param bookId
     * @param quantity
     * @throws GestionAppException If can't find the book. Or if the new stock is less than 0. Or if there is an issue trying to update the book.
     */
    public void decreaseStock(String bookId, int quantity) throws GestionAppException {
        Book book = null;

        try {
            book = this.genericDAO.findOne(bookId);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }

        Validate.notNull(book, "Cannot update the stock for a null object.");
        int currentStock = book.getCurrentStock();
        int newStock = currentStock - quantity;

        if (newStock < 0) throw new GestionAppException("Cannot sale this book. Current stock: " + currentStock + ". Required: " + quantity);

        book.setCurrentStock(newStock);

        try {
            book = this.genericDAO.update(book);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
}
