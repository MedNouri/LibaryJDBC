package application.dao;

import java.util.List;

public interface DAO<T>
{
    public abstract boolean create(T obj);
    public abstract int delete(int id);
    public abstract boolean update(T obj);
    public abstract T find(String name);
    public List<T> getAll () ;
}