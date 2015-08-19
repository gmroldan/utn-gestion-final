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
    
    public String saveBook(Book book) throws GestionAppException {
        return BookService.getInstance().saveBook(book);
    }
    
    public Book updateBook(Book book) throws GestionAppException {
        return BookService.getInstance().updateBook(book);
    }
    
    public void deleteBook(Book book) throws GestionAppException {
        BookService.getInstance().deleteBook(book);
    }
    
    public Book findOne(String id) throws GestionAppException {
        return BookService.getInstance().findOne(id);
    }
    
    public List<Book> findAll() throws GestionAppException {
        return BookService.getInstance().findAll();
    }
    
    public List<Category> findAllCategories() throws GestionAppException {
        return BookService.getInstance().findAllCategories();
    }

    public List<Book> findBooksBySearch(String searchString) throws GestionAppException {
        return BookService.getInstance().findBooksBySearch(searchString);
    }
}
