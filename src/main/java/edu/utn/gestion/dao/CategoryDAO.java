package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Category;
import java.util.List;

/**
 *
 * @author martin
 */
public class CategoryDAO extends GenericDAO<Category, Long> {
    private static final CategoryDAO INSTANCE = new CategoryDAO();

    private CategoryDAO() {
        super(Category.class);
    }
    
    public static CategoryDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Category> findObjectsBySearch(String searchString) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
