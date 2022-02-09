package service;

import model.Category;
import model.Item;
import model.User;

import java.util.List;

public interface Store {
    Item add(Item item);
    Item findById(int id);
    boolean update(int id, Item item);
    List<Item> findAll(User user);
    List<Item> allOrUnfulfilled(User user);
    User addUser(User user);
    User findByName(String name);
    List<Category> allCategory();
}
