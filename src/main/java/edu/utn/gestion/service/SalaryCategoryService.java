package edu.utn.gestion.service;

import edu.utn.gestion.dao.SalaryCategoryDAO;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.SalaryCategory;
import edu.utn.gestion.service.generic.GenericService;

import java.util.List;

/**
 * Created by ASUS on 28/02/2016.
 */
public class SalaryCategoryService extends GenericService<SalaryCategory,String>{
    private static final SalaryCategoryService INSTANCE = new SalaryCategoryService();

    private SalaryCategoryService() {
        super(SalaryCategoryDAO.getInstance());
    }

    public static SalaryCategoryService getInstance() {
        return INSTANCE;
    }

    @Override
    public List findBySearch(String searchString) throws GestionAppException {
        return null;
    }
}
