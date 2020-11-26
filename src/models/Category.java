package models;

import java.util.Objects;

public class Category {
    private int _id;
    private String _title;
    private String _visuel;

    public Category(int id, String title, String visuel) {
        if (id < 0)
            throw new IllegalArgumentException("id can't be a negative");
        this._id = id;

        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("\"Title\" can't be empty or null");
        this._title = title;

        if (visuel == null || visuel.trim().isEmpty())
            throw new IllegalArgumentException("\"Visuel\" can't be empty or null");
        this._visuel = visuel;
    }

    public Category(String title, String visuel) {
        this(0, title, visuel);
    }

    public Category(int id) {
        this(id, "null", "null");
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("id can't be a negative");
        this._id = id;
    }

    public String getTitle() {
        return this._title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("\"Title\" can't be empty or null");
        this._title = title;
    }

    public String getVisuel() {
        return this._visuel;
    }

    public void setVisuel(String visuel) {
        if (visuel == null || visuel.trim().isEmpty())
            throw new IllegalArgumentException("\"Visuel\" can't be empty or null");
        this._visuel = visuel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return _id == category._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, _title, _visuel);
    }

    @Override
    public String toString() {
        return getTitle();
    }

}
