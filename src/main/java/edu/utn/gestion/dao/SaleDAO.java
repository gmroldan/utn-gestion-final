package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Sale;
import edu.utn.gestion.model.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 08/12/15.
 */
public class SaleDAO extends GenericDAO<Sale, Long> {
    private static final Logger LOGGER = Logger.getLogger(SaleDAO.class);
    private static final SaleDAO INSTANCE = new SaleDAO();
    private static final String QUERY_FIND_BY_EMPLOYEE_AND_PERIOD
            = "select * from sale where employee_id = :employee_id and MONTH(date) = :month and YEAR(date) = :year";
    private static final String QUERY_FIND_BY_PERIOD
            = "select * from sale where MONTH(date) = :month and YEAR(date) = :year";

    private SaleDAO() {
        super(Sale.class);
    }

    public static SaleDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Sale> findObjectsBySearch(String searchString) throws DataAccessException {
        return null;
    }

    public List<Sale> findByEmployeeAndPeriod(Employee employee, int month, int year) throws DataAccessException {
        List<Sale> resultList = null;

        try {
            this.startOperation();
            SQLQuery query = this.session.createSQLQuery(QUERY_FIND_BY_EMPLOYEE_AND_PERIOD);
            query.setLong("employee_id", employee.getId());
            query.setInteger("month", month);
            query.setInteger("year", year);
            query.addEntity(Sale.class);

            LOGGER.info(query);

            resultList = query.list();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }

        return CollectionUtils.isNotEmpty(resultList) ? resultList : new ArrayList<>();
    }

    public List<Sale> findByPeriod(int month, int year) throws DataAccessException {
        List<Sale> resultList = null;

        try {
            this.startOperation();
            SQLQuery query = this.session.createSQLQuery(QUERY_FIND_BY_PERIOD);
            query.setInteger("month", month);
            query.setInteger("year", year);
            query.addEntity(Sale.class);

            LOGGER.info(query);

            resultList = query.list();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }

        return CollectionUtils.isNotEmpty(resultList) ? resultList : new ArrayList<>();
    }
}
