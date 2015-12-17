package edu.utn.gestion.util;

import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;

public class BookFactory {
    private static long counter = 1L;
    
    public static Book createBook() {
        Book book = new Book("12345" + counter
                , "TestTitle" + counter
                , "TestDescription" + counter
                , 20, 30
                , new Category()
                , "TestAuthor" + counter
                , "TestEditorial" + counter
                , 3);
        
        counter++;
        
        return book;        
    }
}
