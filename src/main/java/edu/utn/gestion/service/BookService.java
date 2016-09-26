package edu.utn.gestion.service;

import edu.utn.gestion.dao.BookDAO;
import edu.utn.gestion.dao.CategoryDAO;
import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import edu.utn.gestion.service.generic.GenericService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import java.util.List;

/**
 *
 * @author martin
 */
public class BookService extends GenericService<Book, String> {
    private static final BookService INSTANCE = new BookService();
    private  static final Logger LOGGER = Logger.getLogger(BookService.class);

    private final BookDAO bookDAO = BookDAO.getInstance();

    /**
     * Class constructor.
     */
    private BookService() {}

    /**
     * Returns the unique instance of BookService.
     *
     * @return
     */
    public static BookService getInstance() {
        return INSTANCE;
    }

    @Override
    protected GenericDAO<Book, String> getDAO() {
        return this.bookDAO;
    }

    @Override
    public List<Book> findBySearch(String searchString) throws GestionAppException {
        try {
            return this.bookDAO.findObjectsBySearch(searchString);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }

    public List<Book> findBooksWithMinStock() throws GestionAppException {
        try {
            return this.bookDAO.findBooksWithMinStock();
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
     * @param bookId Cannot be null or empty.
     * @param quantity
     * @throws GestionAppException If can't find the book. Or if the new stock is less than 0. Or if there is an issue trying to update the book.
     */
    public void decreaseStock(final String bookId, final int quantity)
            throws GestionAppException {

        if (StringUtils.isEmpty(bookId)) {
            String errorMessage = "Cannot decrease the stock for a book without the bookId.";
            LOGGER.error(errorMessage);
            throw new GestionAppException(errorMessage);
        }

        LOGGER.info("Updating stock for the book with id = " + bookId);

        Book book = null;

        try {
            book = this.bookDAO.findOne(bookId);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }

        Validate.notNull(book, "Cannot update the stock for a null object.");
        int currentStock = book.getCurrentStock();
        int newStock = currentStock - quantity;

        if (newStock < 0) throw new GestionAppException("Cannot sale this book. Current stock: " + currentStock + ". Required: " + quantity);

        book.setCurrentStock(newStock);

        try {
            this.bookDAO.update(book);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
}
