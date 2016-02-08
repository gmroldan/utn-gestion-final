package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Book;
import org.hibernate.Query;

import java.util.List;

/**
 *
 * @author gerardo
 */
public class BookDAO extends GenericDAO<Book, String> {
    private static final BookDAO INSTANCE = new BookDAO();
    private final String QUERY_FIND_BOOKS_BY_SEARCH = "from Book where author like :parm or title like :parm or isbn like :parm or editorial like :parm";
    private final String QUERY_FIND_BOOKS_WITH_MIN_STOCK = "from Book where currentStock <= minimumStock";

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

    /**
     * Returns a list with all the books where the currentStock is less than the minimumStock.
     *
     * @return
     * @throws DataAccessException
     */
    public List<Book> findBooksWithMinStock() throws DataAccessException {
        List<Book> result = null;

        try {
            this.startOperation();
            Query query = this.session.createQuery(QUERY_FIND_BOOKS_WITH_MIN_STOCK);
            result = query.list();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }

        return result;
    }
}
