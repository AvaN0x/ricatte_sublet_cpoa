package models;

import java.util.Objects;
import java.util.ArrayList;

public class Category {
    private int _id;
    private String _title;
    private String _visuel;

    public Category(int _id, String _title, String _visuel) {
        this._id = _id;
        this._title = _title;
        this._visuel = _visuel;
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_title() {
        return this._title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_visuel() {
        return this._visuel;
    }

    public void set_visuel(String _visuel) {
        this._visuel = _visuel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return _id == category._id && Objects.equals(_title, category._title)
                && Objects.equals(_visuel, category._visuel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, _title, _visuel);
    }

    @Override
    public String toString() {
        return "{" + " _id='" + get_id() + "'" + ", _title='" + get_title() + "'" + ", _visuel='" + get_visuel() + "'"
                + "}";
    }

}
