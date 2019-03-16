package repository;


public interface IRepositoryUser<ID,T> {
    int size();
    void save(T entity);
    void delete(ID id);
    void update(ID id,T entity);
    T findOne(ID id);
    Iterable<T> findAll();
}
