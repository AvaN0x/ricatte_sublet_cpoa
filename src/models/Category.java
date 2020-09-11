package models;

import java.util.Objects;

public class Category {
    public int id;
    public String title;
    public String visuel;
    
    public Category(int id, String title, String visuel) {
        this.id = id;
        this.title = title;
        this.visuel = visuel;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVisuel() {
        return this.visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    public Category id(int id) {
        this.id = id;
        return this;
    }

    public Category title(String title) {
        this.title = title;
        return this;
    }

    public Category visuel(String visuel) {
        this.visuel = visuel;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return id == category.id && Objects.equals(title, category.title) && Objects.equals(visuel, category.visuel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, visuel);
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
