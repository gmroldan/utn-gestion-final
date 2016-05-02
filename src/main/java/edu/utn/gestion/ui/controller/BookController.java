package edu.utn.gestion.ui.controller;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import edu.utn.gestion.service.BookService;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.ui.controller.generic.GenericController;
import java.util.List;

public class BookController extends GenericController<Book, String> {
    private final BookService service = BookService.getInstance();

    public List<Category> findAllCategories() throws GestionAppException {
        return this.service.findAllCategories();
    }

    public List<Book> findBySearch(String searchString) throws GestionAppException {
        return this.service.findBySearch(searchString);
    }

    @Override
    protected GenericService<Book, String> getService() {
        return this.service;
    }
}
