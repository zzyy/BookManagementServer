package com.thoughtworks.HSBC.BookManagement.service;

import com.thoughtworks.HSBC.BookManagement.entity.Book;
import com.thoughtworks.HSBC.BookManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Optional<Book> optionalBook = bookRepository.findById(isbn);
        return optionalBook.orElse(null);
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(String isbn, Book updatedBook) {
        if (bookRepository.existsById(isbn)) {
            updatedBook.setIsbn(isbn);
            return bookRepository.save(updatedBook);
        }
        return null;
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}

