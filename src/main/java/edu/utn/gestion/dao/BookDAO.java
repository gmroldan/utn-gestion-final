package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Book;
import org.hibernate.SQLQuery;

import java.util.List;

/**
 *
 * @author gerardo
 */
public class BookDAO extends GenericDAO<Book, String> {
    private static final BookDAO INSTANCE = new BookDAO();
    private final String QUERY_FIND_BOOKS_BY_SEARCH = "from Book where author like :parm or title like :parm or isbn like :parm or editorial like :parm";
    private final String QUERY_FIND_BOOKS_WITH_MIN_STOCK
            = "SELECT DISTINCT b.* FROM book AS b, order_to_supplier AS o, order_detail AS od WHERE\t\n" +
            "    od.book_id = b.isbn AND\n" +
            "    o.id = od.order_detail_id AND    \n" +
            "    b.currentStock <= b.minimumStocK AND\n" +
            "    o.status NOT IN (\"NEW_ORDER\", \"IN_PROCESS\") AND\n" +
            "    b.isbn not in (SELECT od.book_id FROM order_to_supplier as o, order_detail as od \n" +
            "\t\twhere o.status in (\"IN_PROCESS\", \"NEW_ORDER\") AND\n" +
            "        o.id = od.order_detail_id);";// TODO: Replace it for a HQL query

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
            SQLQuery query = this.session.createSQLQuery(QUERY_FIND_BOOKS_WITH_MIN_STOCK);
            query.addEntity(Book.class);
            result = query.list();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }

        return result;
    }
}
