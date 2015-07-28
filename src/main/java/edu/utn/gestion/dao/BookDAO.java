/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.gestion.dao;

import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.model.Book;

/**
 *
 * @author gerardo
 */
public class BookDAO extends GenericDAO<Book, Long> {
    private static final BookDAO INSTANCE = new BookDAO();

    private BookDAO() {
        super(Book.class);
    }
    
    public static final BookDAO getInstance() {
        return INSTANCE;
    }
}
