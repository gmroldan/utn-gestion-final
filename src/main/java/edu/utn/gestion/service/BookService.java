package edu.utn.gestion.service;

import edu.utn.gestion.dao.BookDAO;
import edu.utn.gestion.dao.CategoryDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import java.util.List;

/**
 *
 * @author martin
 */
public class BookService {
    private static final BookService INSTANCE = new BookService();
    private final BookDAO bookDAO = BookDAO.getInstance();

    /**
     * Class constructor.
     */
    private BookService() {
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
    
    public String saveBook(Book book) throws GestionAppException {
        try {
            return this.bookDAO.save(book);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
    
    public Book updateBook(Book book) throws GestionAppException {
        try {
            return this.bookDAO.update(book);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
    
    public void deleteBook(Book book) throws GestionAppException {
        try {
            this.bookDAO.delete(book);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
    
    public Book findOne(String id) throws GestionAppException {
        try {
            return this.bookDAO.findOne(id);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
    
    public List<Book> findBooksBySearch(String searchString) throws GestionAppException {
        try {
            return this.bookDAO.findBooksBySearch(searchString);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
    
    public List<Book> findAll() throws GestionAppException {
        try {
            return this.bookDAO.findAll();
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
}
