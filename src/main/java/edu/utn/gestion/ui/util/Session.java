package edu.utn.gestion.ui.util;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.User;
import edu.utn.gestion.model.UserRole;
import org.apache.log4j.Logger;

/**
 * Created by martin on 27/07/16.
 */
public class Session {
    private static final Logger LOGGER = Logger.getLogger(Session.class);
    private static User CURRENT_USER;

    /**
     * Initializes a new session for a given user.
     *
     * @param user
     * @throws GestionAppException
     */
    public static void init(final User user) throws GestionAppException {
        if (CURRENT_USER != null) {
            throw new GestionAppException("There is an open session for the given user.");
        }

        CURRENT_USER = user;
        LOGGER.info("User " + CURRENT_USER.getName() + " has started a new session.");
    }

    /**
     * Returns the current user.
     *
     * @return
     */
    public static User getCurrentUser() {
        return CURRENT_USER;
    }

    /**
     * Returns true if the current user has the Admin role.
     *
     * @return
     */
    public static boolean isCurrentUserAdmin() {
        return UserRole.ADMIN.equals(CURRENT_USER.getUserRole());
    }

    /**
     * Returns true if the current user has the Vendedor role.
     *
     * @return
     */
    public static boolean isCurrentUserVendedor() {
        return UserRole.VENDEDOR.equals(CURRENT_USER.getUserRole());
    }

    /**
     * Returns true if the current user has the Administrativo role.
     *
     * @return
     */
    public static boolean isCurrentUserAdministrativo() {
        return UserRole.ADMINISTRATIVO.equals(CURRENT_USER.getUserRole());
    }

    /**
     * Returns a String with the user name and the employee name.
     *
     * @return
     */
    public static String toStringCurrentUser() {
        return new StringBuilder()
                .append(CURRENT_USER.getName())
                .append(" - ")
                .append(CURRENT_USER.getEmployee().getName())
                .toString();
    }
}
