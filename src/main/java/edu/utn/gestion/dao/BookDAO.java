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
    public List<Book> findBooksBySearch(String searchString) throws DataAccessException {        
        List<Book> result = null;
        
        try {
            this.startOperation();
            Query query = this.session.createQuery(QUERY_FIND_BOOKS_BY_SEARCH)
                    .setString("parm", "%" + searchString + "%");
            result = query.list();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }
        
        return result;
    }
}
