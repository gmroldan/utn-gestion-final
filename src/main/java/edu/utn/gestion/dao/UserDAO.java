package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.User;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import java.util.List;

/**
 * Created by martin on 26/07/16.
 */
public class UserDAO extends GenericDAO<User, Long> {
    private static final  UserDAO INSTANCE = new UserDAO();
    private final String QUERY_FIND_USER_BY_SEARCH = "from User where name like :parm";
    private final String QUERY_FIND_USER_BY_EMPLOYEE = "select * from user where employee_id = :parm"; // TODO: Replace it for a HQL query.
    private final String QUERY_CHANGE_PASSWORD = "update user set password = :parm_pass where id = :parm_id"; // TODO: Replace it for a HQL query.
    private final String QUERY_FIND_ACTIVE_USERS = "from User where active = true";

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

    /**
     * Updates the password for a given user.
     *
     * @param user
     * @throws DataAccessException
     */
    public void changePassword(final User user) throws DataAccessException {
        try {
            this.startOperation();
            SQLQuery query = this.session.createSQLQuery(QUERY_CHANGE_PASSWORD);
            query.setString("parm_pass", user.getPassword());
            query.setLong("parm_id", user.getId());
            query.addEntity(User.class);
            query.executeUpdate();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }
    }

    /**
     * Returns a List with all the active users.
     *
     * @return
     * @throws DataAccessException
     */
    public List<User> findActiveUsers() throws DataAccessException {
        List<User> result = null;

        try {
            this.startOperation();
            Query query = this.session.createQuery(QUERY_FIND_ACTIVE_USERS);
            result = query.list();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }

        return result;
    }
}
