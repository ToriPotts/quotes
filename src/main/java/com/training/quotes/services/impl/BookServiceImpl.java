

package com.training.quotes.services.impl;

import com.training.quotes.models.Book;
import com.training.quotes.repositories.BookRepository;
import com.training.quotes.services.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(String titleSearch, Integer pagesNumber) {
        List<Book> booksMatchingTitleSearch = new ArrayList<Book>();
        List<Book> booksMatchingPageSearch = new ArrayList<Book>();

        if (titleSearch != null) {
            booksMatchingTitleSearch = this.bookRepository.findAllByTitleIsContaining(titleSearch);
        }
        if (pagesNumber != null) {
            booksMatchingPageSearch = this.bookRepository.findAllByPagesIsGreaterThanEqual(pagesNumber);
        }
        if (titleSearch != null && pagesNumber != null) {
            booksMatchingPageSearch.retainAll(booksMatchingTitleSearch);
            return booksMatchingPageSearch;
        }
        if (titleSearch != null && pagesNumber == null) {
            return booksMatchingTitleSearch;
        }
        if (titleSearch == null && pagesNumber != null) {
            return booksMatchingPageSearch;
        }
        return this.bookRepository.findAll();

    }

    public Book getBookById(int id) {
        return this.bookRepository.findById(id).get();
    }

    public Book addBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteById(Integer id) {
        this.bookRepository.deleteById(id);

    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
}
