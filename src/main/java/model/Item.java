package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private String created;
    private boolean done;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Item(int id, String description, String created, boolean done, User user) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
        this.user = user;
    }

    public Item(String description, String created, boolean done, User user) {
        this.description = description;
        this.created = created;
        this.done = done;
        this.user = user;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id && done == item.done && Objects.equals(description, item.description) && Objects.equals(created, item.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, done);
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", description='" + description + '\''
                + ", created='" + created + '\'' + ", done=" + done
                + ", user=" + user + '}';
    }
}
