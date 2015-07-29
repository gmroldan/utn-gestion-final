package edu.utn.gestion.service;

import edu.utn.gestion.dao.BookDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
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
    
    public Book findOne(long id) throws GestionAppException {
        try {
            return this.bookDAO.findOne(id);
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
}
