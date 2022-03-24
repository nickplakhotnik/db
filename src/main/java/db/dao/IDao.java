package db.dao;

import java.util.List;

public interface IDao<T> {

    T get(int id);

    List<T> getAll();

    void insert(T t);

    void update(T t);

    void delete(T t);

}
