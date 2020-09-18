package dao;

public interface DAO<T> {
    public abstract T getById(int id) throws Exception;

    public abstract boolean create(T objet) throws Exception;

    public abstract boolean update(T objet) throws Exception;

    public abstract boolean delete(T objet) throws Exception;

}
