package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import service.HbnStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AuthServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = HbnStore.instOf().findByName(name);
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        if (user != null) {
            if (user.getPassword().equals(password)) {
                HttpSession sc = req.getSession();
                sc.setAttribute("user", user);
            } else {
                String json = GSON.toJson("Не верный пароль");
                output.write(json.getBytes(StandardCharsets.UTF_8));
                output.flush();
                output.close();
            }
        } else {
            String json = GSON.toJson("Не верный имя или пароль");
            output.write(json.getBytes(StandardCharsets.UTF_8));
            output.flush();
            output.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession sc = req.getSession();
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        if (sc.getAttribute("user") != null) {
            String json = GSON.toJson(sc.getAttribute("user"));
            output.write(json.getBytes(StandardCharsets.UTF_8));
            output.flush();
            output.close();
        }
    }
}
