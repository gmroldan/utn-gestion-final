package edu.utn.gestion.ui.controller;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import edu.utn.gestion.service.BookService;
import java.util.List;

public class BookController {
    private static final BookController INSTANCE = new BookController();
    
    private BookController() {}
    
    public static BookController getInstance() {
        return INSTANCE;
    }
    
    public Book findOne(long id) throws GestionAppException {
        return BookService.getInstance().findOne(id);
    }
    
    public List<Book> findAll() throws GestionAppException {
        return BookService.getInstance().findAll();
    }
    
    public List<Category> findAllCategories() throws GestionAppException {
        return BookService.getInstance().findAllCategories();
    }
}
