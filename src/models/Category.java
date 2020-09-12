package models;

import java.util.Objects;

public class Category implements IBaseModel {
    private int _id;
    private String _title;
    private String _visuel;

    public Category(int id, String title, String visuel) {
        this._id = id;
        this._title = title;
        this._visuel = visuel;
    }

    /**
     * @return The id of the category.
     */
    public int getId() {
        return this._id;
    }

    /**
     * @param id The new id of the category.
     */
    public void setId(int id) {
        this._id = id;
    }

    /**
     * @return The title of the category.
     */
    public String getTitle() {
        return this._title;
    }

    /**
     * @param title The new title of the category.
     */
    public void setTitle(String title) {
        this._title = title;
    }

    /**
     * @return The path to the image of the category.
     */
    public String getVisuel() {
        return this._visuel;
    }

    /**
     * @param visuel The new path to the image of category.
     */
    public void setVisuel(String visuel) {
        this._visuel = visuel;
    }

    /**
     * Set the id to a new one and returns it.
     * 
     * @param id The new id of the category.
     * @return The id of the category.
     */
    public Category id(int id) {
        this._id = id;
        return this;
    }

    /**
     * Set the title to a new one and returns it.
     * 
     * @param title The new title of the category.
     * @return The title of the caregory.
     */
    public Category title(String title) {
        this._title = title;
        return this;
    }

    /**
     * Set the path to the image to a new one and returns it.
     * 
     * @param visuel The new path to the image of the ccategory.
     * @return The path to the image of the category.
     */
    public Category visuel(String visuel) {
        this._visuel = visuel;
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
        return _id == category._id && Objects.equals(_title, category._title)
                && Objects.equals(_visuel, category._visuel);
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
