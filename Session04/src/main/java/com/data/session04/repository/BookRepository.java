package com.data.session04.repository;

import com.data.session04.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByOrderByIdDesc(Pageable pageable);
}
