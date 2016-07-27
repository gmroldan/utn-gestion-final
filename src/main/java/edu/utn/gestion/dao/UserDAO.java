package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.model.User;

import java.util.List;

/**
 * Created by martin on 26/07/16.
 */
public class UserDAO extends GenericDAO<User, Long> {
    private static final  UserDAO INSTANCE = new UserDAO();
    private final String QUERY_FIND_USER_BY_SEARCH = "from User where name like :parm";

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
}
