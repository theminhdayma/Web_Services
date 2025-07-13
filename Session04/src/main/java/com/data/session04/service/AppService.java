package com.data.session04.service;

import java.util.Optional;

public interface AppService<T,ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    T update(T entity);

    void delete(ID id);
}
