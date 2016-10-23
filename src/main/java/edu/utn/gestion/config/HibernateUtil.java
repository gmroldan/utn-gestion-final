package edu.utn.gestion.config;

import edu.utn.gestion.exception.DataAccessException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author martin
 */
public class HibernateUtil {
    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);
    private static final String PROPERTIES_FILE = "hibernate.cfg.xml";
    private static SessionFactory sessionFactory;    
    
    /**
     * Initializes the hibernate context.
     */
    public static void init() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new AnnotationConfiguration().configure(PROPERTIES_FILE).buildSessionFactory();
            } catch (Throwable ex) {
                LOGGER.error("Hibernate context can't be initialized.");
                throw new ExceptionInInitializerError(ex);
            }
        }
    }
    
    /**
     * Shuts down the hibernate context.
     */
    public static void closeHibernateContext() {
        sessionFactory.close();
    }
    
    public static Session openSession() {
        LOGGER.info("Opening new hibernate session.");
        return sessionFactory.openSession();
    }

    /**
     * Closes an open session.
     *
     * @param session Cannot be null.
     * @throws DataAccessException if the session is null.
     */
    public static void closeSession(final Session session)
            throws DataAccessException {
        if (session == null) {
            throw new DataAccessException("Cannot close a null session.");
        }

        LOGGER.info("Closing current session.");

        session.close();
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
