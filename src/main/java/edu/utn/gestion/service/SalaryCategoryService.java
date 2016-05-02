package edu.utn.gestion.service;

import edu.utn.gestion.dao.SalaryCategoryDAO;
import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.SalaryCategory;
import edu.utn.gestion.service.generic.GenericService;

import java.util.List;

/**
 * Created by ASUS on 28/02/2016.
 */
public class SalaryCategoryService extends GenericService<SalaryCategory,String>{
    private static final SalaryCategoryService INSTANCE = new SalaryCategoryService();
    private final SalaryCategoryDAO salaryCategoryDAO = SalaryCategoryDAO.getInstance();

    /**
     * Class constructor.
     */
    private SalaryCategoryService() {}

    /**
     * Returns the unique instance of SalaryCategoryService.
     *
     * @return
     */
    public static SalaryCategoryService getInstance() {
        return INSTANCE;
    }

    @Override
    protected GenericDAO<SalaryCategory, String> getDAO() {
        return this.salaryCategoryDAO;
    }

    @Override
    public List findBySearch(String searchString) throws GestionAppException {
        return null;
    }
}
