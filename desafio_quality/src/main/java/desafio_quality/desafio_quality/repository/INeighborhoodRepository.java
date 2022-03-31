package desafio_quality.desafio_quality.repository;

import java.util.List;

public interface INeighborhoodRepository <T> {
    List<T> findAll();
}
