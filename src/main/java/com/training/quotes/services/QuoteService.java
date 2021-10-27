package com.training.quotes.services;

import com.training.quotes.models.Quote;

import java.util.List;

public interface QuoteService {
    Quote getQuoteById(int id);

    List<Quote> getAllQuotes(String author, String text);

    void deleteById(Integer id);

    Quote addQuote(Quote quote);

    Quote updateQuote(Quote quote);

}
