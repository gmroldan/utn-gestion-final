package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Book;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author gerardo
 */
public class BookDAO extends GenericDAO<Book, String> {
    private static final BookDAO INSTANCE = new BookDAO();
    private final String QUERY_FIND_BOOKS_BY_SEARCH = "from Book where author like :parm or title like :parm or isbn like :parm or editorial like :parm";

    private BookDAO() {
        super(Book.class);
    }
    
    public static final BookDAO getInstance() {
        return INSTANCE;
    }
    
    @Override
    public List<Book> findObjectsBySearch(String searchString) throws DataAccessException {
        return this.findObjectsBySearch(QUERY_FIND_BOOKS_BY_SEARCH, searchString);
    }
}
