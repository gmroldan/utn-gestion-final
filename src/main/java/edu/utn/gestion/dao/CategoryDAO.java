package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.model.Category;

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
    
}
