package edu.utn.gestion.service;

import edu.utn.gestion.dao.BookDAO;
import edu.utn.gestion.dao.CategoryDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import edu.utn.gestion.service.generic.GenericService;
import java.util.List;

/**
 *
 * @author martin
 */
public class BookService extends GenericService<Book, String> {
    private static final BookService INSTANCE = new BookService();
    private final BookDAO bookDAO;

    /**
     * Class constructor.
     */
    private BookService() {
        super(BookDAO.getInstance());
        this.bookDAO = (BookDAO) this.genericDAO;
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
    
    @Override
    public List<Book> findBySearch(String searchString) throws GestionAppException {
        try {
            return this.bookDAO.findObjectsBySearch(searchString);
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
