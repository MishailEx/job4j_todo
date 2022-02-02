package servlet;

import model.Item;
import org.junit.Test;
import service.HbnStore;
import service.Store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ItemServletTest {

    @Test
    public void whenCreateTask() throws IOException {
        Store store = new HbnStore();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("description")).thenReturn("make test");
        new ItemServlet().doPost(req, resp);
        Collection<Item> all = store.findAll();
        Item check = null;
        for (Item item: all) {
            if (item.getDescription().equals("make test")) {
                check = item;
            }
        }
        assertThat(check, notNullValue());
    }

    @Test
    public void whenUpdateTask() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("item")).thenReturn("{\"id\":1,\"description\":\"Сделать ToList\",\"created\":\"2022-02-02 13:58:46\",\"done\":true}");
        Store store = new HbnStore();
        Item beforeUpdate = store.findById(1);
        new ToDoServlet().doPost(req, resp);
        Item afterUpdate = store.findById(1);
        assertTrue(beforeUpdate.isDone() != afterUpdate.isDone());
    }

}