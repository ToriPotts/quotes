package com.training.quotes.controllers;

import com.training.quotes.models.Book;
import com.training.quotes.services.impl.BookServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    private BookServiceImpl bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks(@RequestParam(required = false) String titleSearch, @RequestParam(required = false) Integer pagesNumber) {

        return this.bookService.getAllBooks(titleSearch, pagesNumber);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return this.bookService.addBook(book);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
        return this.bookService.getBookById(id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteById(@PathVariable Integer id) {
        this.bookService.deleteById(id);
    }

    @PutMapping("books/{id}")
    public Book updateBook(@RequestBody Book book) {
        return this.bookService.updateBook(book);
    }
}
