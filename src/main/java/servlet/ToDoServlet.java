package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Item;
import service.HbnStore;
import service.Store;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToDoServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Store store = new HbnStore();
        Item item = GSON.fromJson(req.getParameter("item"), Item.class);
        item.setDone(!item.isDone());
        store.update(item.getId(), item);
        resp.sendRedirect("http://localhost:8080/job4j_todo");
    }
}
