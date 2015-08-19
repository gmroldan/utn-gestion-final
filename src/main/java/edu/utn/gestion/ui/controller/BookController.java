package edu.utn.gestion.ui.controller;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import edu.utn.gestion.service.BookService;
import edu.utn.gestion.ui.controller.generic.GenericController;
import java.util.List;

public class BookController extends GenericController<Book, String> {
    private static final BookController INSTANCE = new BookController();
    private final BookService bookService;
    
    private BookController() {
        super(BookService.getInstance());
        this.bookService = (BookService) this.genericService;
    }
    
    public static BookController getInstance() {
        return INSTANCE;
    }
    
    public List<Category> findAllCategories() throws GestionAppException {
        return BookService.getInstance().findAllCategories();
    }

    public List<Book> findBooksBySearch(String searchString) throws GestionAppException {
        return BookService.getInstance().findBooksBySearch(searchString);
    }
}
