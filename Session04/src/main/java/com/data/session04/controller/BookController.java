package com.data.session04.controller;

import com.data.session04.entity.Book;
import com.data.session04.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String listBooks(Model model,
                            @RequestParam(defaultValue = "0") int page) {
        Page<Book> bookPage = bookService.getAll(page, 5);
        model.addAttribute("bookPage", bookPage);
        return "book/bookList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/bookForm";
    }

    @PostMapping("/add")
    public String saveNewBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id).orElse(null);
        if (book == null) return "redirect:/books";
        model.addAttribute("book", book);
        return "book/bookForm";
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
