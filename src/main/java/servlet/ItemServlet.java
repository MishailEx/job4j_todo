package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Item;
import model.User;
import service.HbnStore;
import service.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


public class ItemServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Store store = HbnStore.instOf();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Collection<Item> items = store.findAll(user);
            resp.setContentType("application/json; charset=utf-8");
            OutputStream output = resp.getOutputStream();
            String json = GSON.toJson(items);
            output.write(json.getBytes(StandardCharsets.UTF_8));
            output.flush();
            output.close();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Store store = HbnStore.instOf();
        User user = (User) req.getSession().getAttribute("user");
        Item item = new Item(req.getParameter("description"),  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), false, user);
        store.add(item);
        resp.sendRedirect("http://localhost:8080/job4j_todo");
    }
}
