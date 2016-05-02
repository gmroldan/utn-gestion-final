package edu.utn.gestion.ui.controller.generic;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.ui.dialog.generic.GenericTableModel;

import java.util.List;

/**
 * Generic behavior for controllers.
 * 
 * @author Gerardo Martín Roldán
 * 
 * @param <E> Entity class.
 * @param <I> Entity class's id.
 */
public abstract class GenericController<E, I> {
    
    public I save(E book) throws GestionAppException {
        return this.getService().save(book);
    }
    
    public E update(E book) throws GestionAppException {
        return this.getService().update(book);
    }
    
    public void delete(E book) throws GestionAppException {
        this.getService().delete(book);
    }
    
    public E findOne(I id) throws GestionAppException {
        return this.getService().findOne(id);
    }
    
    public List<E> findAll() throws GestionAppException {
        return this.getService().findAll();
    }
    
    public List<E> findBySearch(String searchString) throws GestionAppException {
        return this.getService().findBySearch(searchString);
    }

    public void exportData(GenericTableModel tableModel) throws GestionAppException {
        this.getService().exportData(tableModel);
    }

    protected abstract GenericService<E, I> getService();
}
