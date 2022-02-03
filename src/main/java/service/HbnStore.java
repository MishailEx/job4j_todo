package service;

import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HbnStore implements Store {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new HbnStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    @Override
    public Item add(Item item) {
        this.tx(session -> session.save(item));
        return item;
    }

    @Override
    public Item findById(int id) {
        return this.tx(session -> session.get(Item.class, id));
    }

    @Override
    public boolean update(int id, Item item) {
        this.tx(session -> {
            session.update(item);
            return true;
        });
        return false;
    }

    @Override
    public List<Item> findAll() {
        return this.tx(
                session -> session.createQuery("from model.Item").list());
    }

    @Override
    public List<Item> allOrUnfulfilled() {
        List<Item> all = findAll();
        List<Item> task = new ArrayList<>();
        for (Item item: all) {
            if (!item.isDone()) {
                task.add(item);
            }
        }
        return task;
    }
}
