package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.SalaryCategory;

import java.util.List;

/**
 * Created by ASUS on 28/02/2016.
 */
public class SalaryCategoryDAO extends GenericDAO<SalaryCategory,String>{

    private static final SalaryCategoryDAO INSTANCE = new SalaryCategoryDAO();

    private SalaryCategoryDAO() {
        super(SalaryCategory.class);
    }

    public static SalaryCategoryDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List findObjectsBySearch(String searchString) throws DataAccessException {
        return null;
    }
}
