package cz.cvut.fit.si1.server.business.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, S, D, K> {
    T create(S createDto) throws Exception;

    T update(K id, S createDto) throws Exception;

    List<T> findAll();

    Optional<D> findById(K id) throws Exception;

    Optional<T> findByIdAsDto(K id) throws Exception;

    void deleteById(K id) throws Exception;

    Optional<T> toDto(Optional<D> entity);

    T toDto(D entity);
}

