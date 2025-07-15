package com.data.session06.service;

import com.data.session06.entity.Book;
import com.data.session06.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    Book updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            return bookRepository.save(book);
        }
        return null;
    }
}
