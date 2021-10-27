package com.training.quotes.controllers;

import com.training.quotes.models.Quote;
import com.training.quotes.services.impl.QuoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
@CrossOrigin
public class QuoteController {

    @Autowired
    private QuoteServiceImpl quoteService;

    @GetMapping("/{id}")
    public Quote getQuoteById(@PathVariable int id) {
        return this.quoteService.getQuoteById(id);
    }

    @GetMapping("/")
    public List<Quote> getAllQuotes(@RequestParam(required = false) String author, @RequestParam(required = false) String text) {
        return this.quoteService.getAllQuotes(author, text);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        this.quoteService.deleteById(id);
    }

    @PostMapping("/quotes")
    public Quote addQuote(@RequestBody Quote quote) {
        return this.quoteService.addQuote(quote);
    }

    @PutMapping("quotes/{id}")
    public Quote updateQuotes(@RequestBody Quote quote) {
        return this.quoteService.updateQuote(quote);
    }


}
