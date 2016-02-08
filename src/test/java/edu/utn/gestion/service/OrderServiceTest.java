package edu.utn.gestion.service;

import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.OrderDetail;
import edu.utn.gestion.util.BookFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by martin on 29/12/15.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({BookService.class})
public class OrderServiceTest {
    private OrderService orderServiceMock;

    @Before
    public void setUp() throws Exception {
        this.orderServiceMock = mock(OrderService.class);
    }

    @Test
    public void getBooksForNewOrderTest() throws Exception {
        List<Book> bookList = Arrays.asList(BookFactory.createBook(), BookFactory.createBook());

        List<OrderDetail> result = this.getBooksForNewOrderPrepareForTest(bookList);

        int defaultQuantityToOrder = (int) Whitebox.getInternalState(this.orderServiceMock, "DEFAULT_QUANTITY_TO_ORDER");

        assertEquals(bookList.get(0), result.get(0).getBook());
        assertEquals(defaultQuantityToOrder, result.get(0).getQuantity());
        assertEquals(bookList.get(1), result.get(1).getBook());
        assertEquals(defaultQuantityToOrder, result.get(1).getQuantity());
    }

    @Test
    public void getBooksForNewOrderWithEmptyListTest() throws Exception {
        List<OrderDetail> result = this.getBooksForNewOrderPrepareForTest(new ArrayList<>());
        assertTrue(result.isEmpty());
    }

    private List<OrderDetail> getBooksForNewOrderPrepareForTest(List<Book> bookList) throws Exception {
        BookService bookServiceMock = mock(BookService.class);

        mockStatic(BookService.class);
        when(BookService.getInstance()).thenReturn(bookServiceMock);
        when(bookServiceMock.findBooksWithMinStock()).thenReturn(bookList);

        doCallRealMethod().when(this.orderServiceMock).getBooksForNewOrder();

        return this.orderServiceMock.getBooksForNewOrder();
    }
}