package models;

import java.util.Objects;

public class Category {
    private int _id;
    private String _title;
    private String _visuel;

    public Category(int id, String title, String visuel) {
        this._id = id;
        this._title = title;
        this._visuel = visuel;
    }

    public Category(String title, String visuel) {
        this._id = -1;
        this._title = title;
        this._visuel = visuel;
    }

    public Category(int id) {
        this._id = id;
        this._title = "";
        this._visuel = "";
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getTitle() {
        return this._title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getVisuel() {
        return this._visuel;
    }

    public void setVisuel(String visuel) {
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
        return "{" + " _id='" + getId() + "'" + ", _title='" + getTitle() + "'" + ", _visuel='" + getVisuel() + "'"
                + "}";
    }

}
