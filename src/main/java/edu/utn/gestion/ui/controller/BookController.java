package edu.utn.gestion.ui.controller;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import edu.utn.gestion.service.BookService;
import edu.utn.gestion.ui.controller.generic.GenericController;
import java.util.List;

public class BookController extends GenericController<Book, String> {
    private final BookService bookService;
    
    public BookController() {
        super(BookService.getInstance());
        this.bookService = (BookService) this.genericService;
    }

    public List<Category> findAllCategories() throws GestionAppException {
        return BookService.getInstance().findAllCategories();
    }

    public List<Book> findBySearch(String searchString) throws GestionAppException {
        return BookService.getInstance().findBySearch(searchString);
    }
}
