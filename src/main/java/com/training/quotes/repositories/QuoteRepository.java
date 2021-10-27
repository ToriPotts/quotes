package com.training.quotes.repositories;

import com.training.quotes.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findAllByAuthorIsContaining(String searchTerm);

    List<Quote> findAllByTextIsContaining(String textSearchTerm);
}
