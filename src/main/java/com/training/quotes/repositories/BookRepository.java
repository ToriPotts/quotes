package com.training.quotes.repositories;

import com.training.quotes.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByTitleIsContaining(String searchTerm);

    List<Book> findAllByPagesIsGreaterThanEqual(Integer pagesNumber);

}
