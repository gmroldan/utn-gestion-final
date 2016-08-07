package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.User;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.SQLQuery;

import java.util.List;

/**
 * Created by martin on 26/07/16.
 */
public class UserDAO extends GenericDAO<User, Long> {
    private static final  UserDAO INSTANCE = new UserDAO();
    private final String QUERY_FIND_USER_BY_SEARCH = "from User where name like :parm";
    private final String QUERY_FIND_USER_BY_EMPLOYEE = "select * from user where employee_id = :parm"; // TODO: Replace it for a HQL query.

    /**
     * Class constructor.
     */
    private UserDAO() {
        super(User.class);
    }

    /**
     * Returns the unique instance of UserDAO.
     *
     * @return
     */
    public static UserDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> findObjectsBySearch(String searchString) throws DataAccessException {
        return this.findObjectsBySearch(QUERY_FIND_USER_BY_SEARCH, searchString);
    }

    /**
     * Returns the user related for a give user.
     *
     * @param employee
     * @return
     * @throws DataAccessException
     */
    public User findUserByEmployee(final Employee employee) throws DataAccessException {
        List<User> result = null;
        try {
            this.startOperation();
            SQLQuery query = this.session.createSQLQuery(QUERY_FIND_USER_BY_EMPLOYEE);
            query.setLong("parm", employee.getId());
            query.addEntity(User.class);
            result = query.list();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }

        return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
    }
}
