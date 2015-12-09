package edu.utn.gestion.ui.controller.generic;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.service.generic.GenericService;
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
    protected final GenericService<E, I> genericService;

    public GenericController(GenericService genericService) {
        this.genericService = genericService;
    }
    
    public I save(E book) throws GestionAppException {
        return this.genericService.save(book);
    }
    
    public E update(E book) throws GestionAppException {
        return this.genericService.update(book);
    }
    
    public void delete(E book) throws GestionAppException {
        this.genericService.delete(book);
    }
    
    public E findOne(I id) throws GestionAppException {
        return this.genericService.findOne(id);
    }
    
    public List<E> findAll() throws GestionAppException {
        return this.genericService.findAll();
    }
    
    public List<E> findBySearch(String searchString) throws GestionAppException {
        return this.genericService.findBySearch(searchString);
    }
}
