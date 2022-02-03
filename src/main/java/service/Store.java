package service;

import model.Item;

import java.util.List;

public interface Store {
    Item add(Item item);
    Item findById(int id);
    boolean update(int id, Item item);
    List<Item> findAll();
    List<Item> allOrUnfulfilled();
}
