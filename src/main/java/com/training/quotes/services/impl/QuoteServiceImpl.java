package com.training.quotes.services.impl;

import com.training.quotes.models.Quote;
import com.training.quotes.repositories.QuoteRepository;
import com.training.quotes.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;

    public Quote getQuoteById(int id) {
        return this.quoteRepository.findById(id).get();
    }

    @Override
    public List<Quote> getAllQuotes(String author, String text) {
        List<Quote> quotesMatchingAuthorSearch = new ArrayList<Quote>();
        List<Quote> quotesMatchingTextSearch = new ArrayList<Quote>();

        if (author != null) {
            quotesMatchingAuthorSearch = this.quoteRepository.findAllByAuthorIsContaining(author);
        }
        if (text != null) {
            quotesMatchingTextSearch = this.quoteRepository.findAllByTextIsContaining(text);
        }
        if (author != null && text != null) {
            quotesMatchingAuthorSearch.retainAll(quotesMatchingTextSearch);
            return quotesMatchingAuthorSearch;
        }
        if (author != null && text == null) {
            return quotesMatchingAuthorSearch;
        }
        if (author == null && text != null) {
            return quotesMatchingTextSearch;
        }
        return this.quoteRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        this.quoteRepository.deleteById(id);
    }

    @Override
    public Quote addQuote(Quote quote) {
        return this.quoteRepository.save(quote);
    }

    @Override
    public Quote updateQuote(Quote quote) {
        return this.quoteRepository.save(quote);
    }
}
