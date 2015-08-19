package edu.utn.gestion.dao.generic;

import edu.utn.gestion.config.HibernateUtil;
import edu.utn.gestion.exception.DataAccessException;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Generic DAO class to work with any entity class.
 * 
 * @author martin
 * @param <T> Entity class that will be used.
 * @param <I> Id type.
 */
public abstract class GenericDAO<T, I> {
    private static final Logger LOGGER = Logger.getLogger(GenericDAO.class);
    private final Class clazz;
    private String findAllQuery;    
    protected Session session;
    private Transaction transaction;

    /**
     * Class constructor.
     * 
     * @param clazz 
     */
    public GenericDAO(Class clazz) {
        this.clazz = clazz;
        this.initQueries();
    }
    
    private void initQueries() {
        this.findAllQuery = new StringBuilder()
                .append("from ")
                .append(this.clazz.getSimpleName())
                .toString();
    }
    
    protected void startOperation() {
        this.session = HibernateUtil.openSession();
        this.transaction = this.session.beginTransaction();
    }
    
    protected void finishOperation() {
        this.transaction.commit();
    }
    
    protected void handleException(Exception ex) throws DataAccessException {
        if (this.transaction != null) {
            this.transaction.rollback();
            LOGGER.error("Transaction rolledback.", ex);
        }
        throw new DataAccessException(ex);
    }
    
    private void validateNotNull(T object) throws DataAccessException {
        if (object == null) {
            throw new DataAccessException("The object to be proccessed can not be null.");
        }
    }
    
    /**
     * Saves an entity class into the DB.
     * 
     * @param object Object to be saved.
     * @return An instance of the object that was saved.
     * @throws edu.utn.gestion.exception.DataAccessException when something goes wrong.
     */
    public I save(T object) throws DataAccessException {
        this.validateNotNull(object);
        
        I result = null;
        
        try {
            this.startOperation();
            result = (I) this.session.save(object);
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }
        
        return result;
    }
    
    /**
     * Updates an object into the DB.
     * 
     * @param object Object to be updated.
     * @return An instance of the object that was updated.
     * @throws edu.utn.gestion.exception.DataAccessException when something goes wrong.
     */
    public T update(T object) throws DataAccessException {
        this.validateNotNull(object);
        
        T result = null;
        
        try {
            this.startOperation();
            result = (T) this.session.merge(object);
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }
        
        return result; 
    }
    
    /**
     * Deletes a given object from the DB.
     * 
     * @param object 
     * @throws edu.utn.gestion.exception.DataAccessException when something goes wrong.
     */
    public void delete(T object) throws DataAccessException {
        this.validateNotNull(object);
        
        try {
            this.startOperation();
            this.session.delete(object);
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }        
    }
    
    /**
     * Retrieves an instance of T from a given id.
     * 
     * @param id
     * @return 
     * @throws edu.utn.gestion.exception.DataAccessException When something goes wrong. Or if the register doesn't exist.
     */
    public T findOne(I id) throws DataAccessException {        
        T result = null;
        
        try {
            this.startOperation();
            result = (T) this.session.get(this.clazz, (Serializable) id);
            this.finishOperation();            
        } catch (Exception ex) {
            this.handleException(ex);
        }
        
        if (result == null) {
            throw new DataAccessException(new StringBuilder()
                    .append("Cannot find data for ")
                    .append(this.clazz.getSimpleName())
                    .append(" with id=")
                    .append(id).toString());
        }
        
        return result;
    }
    
    /**
     * Retrieves a list of objects.
     * 
     * @return 
     * @throws edu.utn.gestion.exception.DataAccessException When something goes wrong.
     */
    public List<T> findAll() throws DataAccessException {
        List<T> result = null;
        
        try {
            this.startOperation();
            Query query = this.session.createQuery(this.findAllQuery);
            result = query.list();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }
        
        return result;
    }
    
    public abstract List<T> findBooksBySearch(String searchString) throws DataAccessException;
}
