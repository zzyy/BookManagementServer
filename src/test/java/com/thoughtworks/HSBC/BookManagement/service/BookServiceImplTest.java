package com.thoughtworks.HSBC.BookManagement.service;

import com.thoughtworks.HSBC.BookManagement.entity.Book;
import com.thoughtworks.HSBC.BookManagement.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void testAddBook() {
        Book bookToAdd = new Book();
        bookToAdd.setIsbn("978-3-16-148410-0");
        bookToAdd.setTitle("Test Driven Development");
        bookToAdd.setAuthor("Kent Beck");
        bookToAdd.setPublishDate(LocalDate.of(2002, 10, 21));

        when(bookRepository.save(bookToAdd)).thenReturn(bookToAdd);

        Book addedBook = bookService.addBook(bookToAdd);

        assertEquals(bookToAdd, addedBook);
        verify(bookRepository, times(1)).save(bookToAdd);
    }

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book();
        book1.setIsbn("978-3-16-148410-0");
        book1.setTitle("Test Driven Development");
        book1.setAuthor("Kent Beck");
        book1.setPublishDate(LocalDate.of(2002, 10, 21));

        Book book2 = new Book();
        book2.setIsbn("978-3-16-148411-0");
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        book2.setPublishDate(LocalDate.of(2008, 8, 11));

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();

        assertEquals(2, books.size());
        assertEquals(book1, books.get(0));
        assertEquals(book2, books.get(1));
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateBook() {
        String isbn = "978-3-16-148410-0";

        Book updatedBook = new Book();
        updatedBook.setIsbn(isbn);
        updatedBook.setTitle("Clean Code");
        updatedBook.setAuthor("Robert C. Martin");
        updatedBook.setPublishDate(LocalDate.of(2008, 8, 11));

        when(bookRepository.existsById(isbn)).thenReturn(true);
        when(bookRepository.save(updatedBook)).thenReturn(updatedBook);

        Book result = bookService.updateBook(isbn, updatedBook);

        assertEquals(updatedBook, result);
        verify(bookRepository, times(1)).existsById(isbn);
        verify(bookRepository, times(1)).save(updatedBook);
    }

    @Test
    public void testDeleteBook() {
        String isbn = "978-3-16-148410-0";

        bookService.deleteBook(isbn);

        verify(bookRepository, times(1)).deleteById(isbn);
    }
}

