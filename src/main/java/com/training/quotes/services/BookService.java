package com.training.quotes.services;

import com.training.quotes.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks(String titleSearch, Integer pageNumber);

    Book getBookById(int id);

    Book addBook(Book book);

    void deleteById(Integer id);

    Book updateBook(Book book);


}
