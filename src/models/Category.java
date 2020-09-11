package models;

import java.util.Objects;

public class Category {
    private int id;
    private String title;
    private String visuel;

    public Category(int id, String title, String visuel) {
        this.id = id;
        this.title = title;
        this.visuel = visuel;
    }

    /**
     * @return The id of the category.
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param id The new id of the category.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The title of the category.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title The new title of the category.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The path to the image of the category.
     */
    public String getVisuel() {
        return this.visuel;
    }

    /**
     * @param visuel The new path to the image of category.
     */
    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    /**
     * Set the id to a new one and returns it.
     * @param id The new id of the category.
     * @return The id of the category.
     */
    public Category id(int id) {
        this.id = id;
        return this;
    }

    /**
     * Set the title to a new one and returns it.
     * @param title The new title of the category.
     * @return The title of the caregory.
     */
    public Category title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the path to the image to a new one and returns it.
     * @param visuel The new path to the image of the ccategory.
     * @return The path to the image of the category.
     */
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
