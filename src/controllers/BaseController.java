package controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;

import models.IBaseModel;

public abstract class BaseController<T extends IBaseModel> {
    protected ArrayList<T> _objects;

    private final Class<T> _type;

    public BaseController() {
        this._type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        getDistantObjects();
    }

    /**
     * Add a new object to the list. The id is automaticly generated.
     * 
     * @param parameters The parameters of the object
     */
    public void addObject(Object... parameters) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, SecurityException {
        var params = new ArrayList<Object>();
        params.add(-1);
        Collections.addAll(params, parameters);
        addObject((T) _type.getDeclaredConstructors()[0].newInstance(params.toArray()));
    }

    /**
     * Add a new object to the list. The id is automaticly generated.
     * 
     * @param obj The object to add.
     */
    protected void addObject(T obj) {
        int lastId = _objects.get(_objects.size() - 1).getId();
        if (obj.getId() < lastId)
            obj.setId(lastId + 1);
        _objects.add(obj);
    };

    /**
     * Edit a object in the list.
     * 
     * @param id         The id of the object
     * @param parameters The parameters of the object
     * @return If the editing is successfull or not.
     */
    protected boolean editObject(int id, Object... parameters)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException {
        for (T object : _objects) {
            if (object.getId() == id) {
                var newFields = new ArrayList<Object>();
                newFields.add(id);
                Collections.addAll(newFields, parameters);
                object = (T) _type.getDeclaredConstructors()[0].newInstance(newFields.toArray());
                return true;
            }
        }
        return false;
    }

    /**
     * Remove an object in the list.
     * 
     * @param categ The object to remove.
     * @return If the removing is sucessfull or not.
     */
    public boolean removeObject(T categ) {
        return removeObject(categ.getId());
    }

    /**
     * Remove a object in the list.
     * 
     * @param id The id of the object
     * @return If the removing is sucessfull or not.
     */
    protected boolean removeObject(int id) {
        return _objects.removeIf(obj -> obj.getId() == id);
    }

    /**
     * Get all the distant objects.
     * 
     * @return The ArrayList containing the objects.
     */
    public abstract ArrayList<T> getDistantObjects();

    /**
     * Get all the objects.
     * 
     * @return The ArrayList containing the objects.
     */
    public ArrayList<T> getObjects() {
        if (_objects == null)
            _objects = new ArrayList<T>();
        return _objects;
    }
}
