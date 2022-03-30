package desafio_quality.desafio_quality.repository;

import java.util.List;

public interface IPropertyRepository <U extends  Number, T> {
    T findById(U id);
    List<T> findAll();
    T insert(T entity);
    T update(U id, T entity);
}
