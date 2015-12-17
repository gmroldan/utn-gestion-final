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
import org.junit.Ignore;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.internal.util.reflection.Whitebox.setInternalState;

// TODO: Fix some tests.
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
    
    @Test @Ignore
    public void saveTest() throws Exception {        
        Book book = BookFactory.createBook();
        String expectedId = book.getIsbn();
        when(this.bookDAOMock.save(book)).thenReturn(expectedId);
        
        String result = this.bookService.save(book);
        
        assertEquals(expectedId, result);
    }
    
    @Test (expected = GestionAppException.class)
    public void saveTestThrowsException() throws Exception {
        when(this.bookDAOMock.save(any(Book.class))).thenThrow(DataAccessException.class);
        this.bookService.save(new Book());
    }
    
    @Test @Ignore
    public void updateTest() throws Exception {
        Book book = BookFactory.createBook();
        when(this.bookDAOMock.update(book)).thenReturn(book);
        
        Book result = this.bookService.update(book);
        
        assertEquals(book, result);
    }
    
    @Test (expected = GestionAppException.class)
    public void updateTestThrowsException() throws Exception {
        when(this.bookDAOMock.update(any(Book.class))).thenThrow(DataAccessException.class);
        this.bookService.update(new Book());
    }
    
    @Test @Ignore
    public void deleteTest() throws Exception {
        Book book = BookFactory.createBook();
        
        this.bookService.delete(book);
        
        verify(bookDAOMock, times(1)).delete(book);
    }

    @Test @Ignore
    public void findOneTest() throws Exception {
        Book book = BookFactory.createBook();
        when(this.bookDAOMock.findOne(anyString())).thenReturn(book);
        
        Book result = this.bookService.findOne("123456789");
        
        assertEquals(book, result);
    }
    
    @Test (expected = GestionAppException.class)
    public void findOneTestThrowsException() throws Exception {
        when(this.bookDAOMock.findOne(anyString())).thenThrow(DataAccessException.class);
        this.bookService.findOne("123456789");
    }

    @Test @Ignore
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

    @Test
    public void decreaseStockTest() throws GestionAppException, DataAccessException {
        Book book = BookFactory.createBook();
        String isbn = book.getIsbn();
        book.setStock(10);

        when(this.bookDAOMock.findOne(isbn)).thenReturn(book);

        this.bookService.decreaseStock(isbn, 2);

        verify(this.bookDAOMock, times(1)).findOne(isbn);
        verify(this.bookDAOMock, times(1)).update(book);
        assertEquals(8, book.getStock());
    }

    @Test (expected = GestionAppException.class)
    public void decreaseStockCannotUpdateBookTest() throws GestionAppException, DataAccessException {
        Book book = BookFactory.createBook();
        String isbn = book.getIsbn();
        book.setStock(10);

        when(this.bookDAOMock.findOne(isbn)).thenReturn(book);
        when(this.bookDAOMock.update(book)).thenThrow(DataAccessException.class);

        this.bookService.decreaseStock(isbn, 2);
    }

    @Test (expected = GestionAppException.class)
    public void decreaseStockIlegalStockTest() throws GestionAppException, DataAccessException {
        Book book = BookFactory.createBook();
        book.setStock(10);

        when(this.bookDAOMock.findOne(anyString())).thenReturn(book);

        this.bookService.decreaseStock(book.getIsbn(), 15);
    }

    @Test (expected = GestionAppException.class)
    public void decreaseStockCannotFindBookTest() throws GestionAppException, DataAccessException {
        when(this.bookDAOMock.findOne(anyString())).thenThrow(DataAccessException.class);
        this.bookService.decreaseStock("123", 2);
    }

    @Test (expected = NullPointerException.class)
    public void decreaseStockNullBookTest() throws GestionAppException, DataAccessException {
        when(this.bookDAOMock.findOne(anyString())).thenReturn(null);
        this.bookService.decreaseStock("123", 2);
    }
}
