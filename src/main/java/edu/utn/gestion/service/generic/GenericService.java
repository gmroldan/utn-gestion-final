package edu.utn.gestion.service.generic;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.FileGenerationException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.service.util.ODFFactory;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Generic behavior for service layer.
 * 
 * @author Gerardo Martín Roldán
 * 
 * @param <E> Entity class.
 * @param <I> Entity class's id.
 */
public abstract class GenericService<E, I> {

    public I save(E object) throws GestionAppException {
        try {
            return this.getDAO().save(object);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
    
    public E update(E object) throws GestionAppException {
        try {
            return this.getDAO().update(object);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
    
    public void delete(E object) throws GestionAppException {
        try {
            this.getDAO().delete(object);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
    
    public E findOne(I id) throws GestionAppException {
        try {
            return this.getDAO().findOne(id);
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }
    
    public List<E> findAll() throws GestionAppException {
        try {
            return this.getDAO().findAll();
        } catch (DataAccessException ex) {
            throw new GestionAppException(ex.getMessage(), ex);
        }
    }

    public void exportData(GenericTableModel tableModel) throws GestionAppException {
        try {
            String fileName = ((Class<E>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
            ODFFactory.doExport(fileName, tableModel);
        } catch (FileGenerationException ex) {
            throw new GestionAppException(ex);
        }
    }

    protected abstract GenericDAO<E, I> getDAO();
    public abstract List<E> findBySearch(String searchString) throws GestionAppException;
}
