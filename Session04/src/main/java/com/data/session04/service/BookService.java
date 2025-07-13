package com.data.session04.service;

import com.data.session04.entity.Book;
import com.data.session04.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements AppService<Book, Long> {

    private final BookRepository bookRepository;

    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }


    public Page<Book> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return bookRepository.findAllByOrderByIdDesc(pageable);
    }


    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }


    @Override
    public Book update(Book book) {
        if (book.getId() != null && bookRepository.existsById(book.getId())) {
            return bookRepository.save(book);
        }
        throw new RuntimeException("Không tìm thấy sách để cập nhật");
    }


    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
