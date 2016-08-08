package edu.utn.gestion.service;

import edu.utn.gestion.dao.UserDAO;
import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.User;
import edu.utn.gestion.service.generic.GenericService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by martin on 26/07/16.
 */
public class UserService extends GenericService<User, Long> {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private static final UserService INSTANCE = new UserService();
    private final UserDAO userDAO = UserDAO.getInstance();

    /**
     * Class constructor.
     */
    private UserService() {}

    /**
     * Returns the unique instance of UserService.
     *
     * @return
     */
    public static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    protected GenericDAO<User, Long> getDAO() {
        return this.userDAO;
    }

    @Override
    public Long save(final User user) throws GestionAppException {
        Validate.notNull(user, "User cannot be null.");

        Employee employee = user.getEmployee();
        Validate.notNull(employee, "Employee cannot be null.");

        User userAux = null;

        try {
            userAux = this.userDAO.findUserByEmployee(employee);
        } catch (DataAccessException e) {
            throw new GestionAppException(e);
        }

        if (userAux != null) {
            throw new GestionAppException(new StringBuilder()
                    .append("The employee ")
                    .append(employee.getName())
                    .append(" already has an user account.")
                    .toString());
        }

        return super.save(user);
    }

    @Override
    public List<User> findBySearch(final String searchString) throws GestionAppException {
        try {
            return this.userDAO.findObjectsBySearch(searchString);
        } catch (DataAccessException e) {
            throw new GestionAppException(e);
        }
    }

    /**
     * Validates if a given username-password combination is correct.
     *
     * @param name
     * @param password
     * @throws GestionAppException If something goes wrong.
     */
    public User login(final String name, final String password)
            throws GestionAppException {
        Validate.notNull(name, "UserName cannot be null");
        Validate.notNull(password, "Password cannot be null");

        List<User> userList = this.findBySearch(name);

        if (CollectionUtils.isEmpty(userList)) {
            throw new GestionAppException("The user " + name + " doesn't exist.");
        }

        User user = userList.get(0);

        if (password.equals(user.getPassword())) {
            return user;
        }

        throw new GestionAppException("Wrong password.");
    }

    /**
     * Sets the employee's cuit as password for a given user.
     *
     * @param user
     * @return The user after being updated.
     * @throws GestionAppException
     */
    public User resetPassword(final User user) throws GestionAppException {
        Validate.notNull(user, "User cannot be null.");

        String newPassword = user.getEmployee().getCuit();
        user.setPassword(newPassword);

        try {
            this.userDAO.changePassword(user);
            return this.findOne(user.getId());
        } catch (DataAccessException e) {
            throw new GestionAppException("The password couldn't be restarted", e);
        }
    }

    /**
     * Deletes an user logically.
     *
     * @param user
     * @throws GestionAppException
     */
    @Override
    public void delete(final User user) throws GestionAppException {
        Validate.notNull(user, "User cannot be null.");

        if (!user.isActive()) {
            String message = new StringBuilder()
                    .append("User ")
                    .append(user.getName())
                    .append(" cannot be deleted because it's not active.")
                    .toString();
            LOGGER.info(message);
            throw new GestionAppException(message);
        }

        user.setActive(false);
        this.update(user);

        LOGGER.info(new StringBuilder()
                .append("User ")
                .append(user.getName())
                .append(" was delete successfully.")
                .toString());
    }

    /**
     * Returns a list with all the active users.
     *
     * @return
     * @throws GestionAppException
     */
    public List<User> findActiveUsers() throws GestionAppException {
        try {
            return this.userDAO.findActiveUsers();
        } catch (DataAccessException ex) {
            LOGGER.error("There was a problem trying to retrieve active users.", ex);
            throw new GestionAppException("There was a problem trying to retrieve active users.", ex);
        }
    }

    /**
     * Enables an user account if the user was deleted.
     *
     * @param user
     * @throws GestionAppException
     */
    public void enableUser(final User user) throws GestionAppException {
        Validate.notNull(user, "User cannot be null.");

        if (user.isActive()) {
            String message = new StringBuilder()
                    .append("User ")
                    .append(user.getName())
                    .append(" cannot be enabled because it's active.")
                    .toString();
            LOGGER.info(message);
            throw new GestionAppException(message);
        }

        user.setActive(true);
        this.update(user);

        LOGGER.info(new StringBuilder()
                .append("User ")
                .append(user.getName())
                .append(" was enable successfully.")
                .toString());
    }
}
