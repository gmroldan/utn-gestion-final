package edu.utn.gestion.service;

import edu.utn.gestion.dao.BookDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.util.BookFactory;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.internal.util.reflection.Whitebox.setInternalState;

public class BookServiceTest {
    private BookService bookService;
    private BookDAO bookDAOMock;
    
    @Before
    public void setUp() {
        this.bookService = BookService.getInstance();
        this.bookDAOMock = mock(BookDAO.class);
        setInternalState(this.bookService, "bookDAO", this.bookDAOMock);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void saveBookTest() throws Exception {        
        Book book = BookFactory.createBook();
        Long expectedId = book.getId();
        when(this.bookDAOMock.save(book)).thenReturn(expectedId);
        
        Long result = this.bookService.saveBook(book);
        
        assertEquals(expectedId, result);
    }
    
    @Test (expected = GestionAppException.class)
    public void saveBookTestThrowsException() throws Exception {
        when(this.bookDAOMock.save(any(Book.class))).thenThrow(DataAccessException.class);
        this.bookService.saveBook(new Book());
    }
    
    @Test
    public void updateBookTest() throws Exception {
        Book book = BookFactory.createBook();
        when(this.bookDAOMock.update(book)).thenReturn(book);
        
        Book result = this.bookService.updateBook(book);
        
        assertEquals(book, result);
    }
    
    @Test (expected = GestionAppException.class)
    public void updateBookTestThrowsException() throws Exception {
        when(this.bookDAOMock.update(any(Book.class))).thenThrow(DataAccessException.class);
        this.bookService.updateBook(new Book());
    }
    
    @Test
    public void deleteBookTest() throws Exception {
        Book book = BookFactory.createBook();
        
        this.bookService.deleteBook(book);
        
        verify(bookDAOMock, times(1)).delete(book);
    }

    @Test
    public void findOneTest() throws Exception {
        Book book = BookFactory.createBook();
        when(this.bookDAOMock.findOne(anyLong())).thenReturn(book);
        
        Book result = this.bookService.findOne(2L);
        
        assertEquals(book, result);
    }
    
    @Test (expected = GestionAppException.class)
    public void findOneTestThrowsException() throws Exception {
        when(this.bookDAOMock.findOne(anyLong())).thenThrow(DataAccessException.class);
        this.bookService.findOne(2L);
    }

    @Test
    public void findAllTest() throws Exception {
        Book book1 = BookFactory.createBook();
        Book book2 = BookFactory.createBook();
        List<Book> bookList = Arrays.asList(book1, book2);
        
        when(this.bookDAOMock.findAll()).thenReturn(bookList);
        
        List<Book> result = this.bookService.findAll();
        
        assertEquals(bookList, result);
    }
    
    @Test (expected = GestionAppException.class)
    public void findAllTestThrowsException() throws Exception {
        when(this.bookDAOMock.findAll()).thenThrow(DataAccessException.class);
        this.bookService.findAll();
    }
}
