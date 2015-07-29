package edu.utn.gestion.service;

import edu.utn.gestion.dao.BookDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
    public void findOneTest() throws Exception {
        Book book = new Book(2L, "TestTitle", "TestDescription", "12345", 20, 30, new Category(), "TestAuthor", "TestEditorial");
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
        Book book1 = new Book(1L, "TestTitle1", "TestDescription1", "12355", 20, 30, new Category(), "TestAuthor", "TestEditorial");
        Book book2 = new Book(2L, "TestTitle2", "TestDescription2", "12345", 20, 30, new Category(), "TestAuthor", "TestEditorial");
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
