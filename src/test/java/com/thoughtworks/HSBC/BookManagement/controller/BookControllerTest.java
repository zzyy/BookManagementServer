package com.thoughtworks.HSBC.BookManagement.controller;

import com.thoughtworks.HSBC.BookManagement.entity.Book;
import com.thoughtworks.HSBC.BookManagement.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book();
        book.setIsbn("978-3-16-148410-0");
        book.setTitle("Test Driven Development");
        book.setAuthor("Kent Beck");
        book.setPublishDate(LocalDate.of(2002, 10, 21));

        when(bookService.addBook(book)).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"978-3-16-148410-0\",\"title\":\"Test Driven Development\",\"author\":\"Kent Beck\",\"publishDate\":\"2002-10-21\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllBooks() throws Exception {
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

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].isbn", is("978-3-16-148410-0")))
                .andExpect(jsonPath("$[0].title", is("Test Driven Development")))
                .andExpect(jsonPath("$[0].author", is("Kent Beck")))
                .andExpect(jsonPath("$[0].publishDate", is("2002-10-21")))
                .andExpect(jsonPath("$[1].isbn", is("978-3-16-148411-0")))
                .andExpect(jsonPath("$[1].title", is("Clean Code")))
                .andExpect(jsonPath("$[1].author", is("Robert C. Martin")))
                .andExpect(jsonPath("$[1].publishDate", is("2008-08-11")));
    }

    @Test
    public void testUpdateBook() throws Exception {
        String isbn = "978-3-16-148411-0";

        Book updatedBook = new Book();
        updatedBook.setIsbn(isbn);
        updatedBook.setTitle("Clean Code: A Handbook of Agile Software Craftsmanship");
        updatedBook.setAuthor("Robert C. Martin");
        updatedBook.setPublishDate(LocalDate.of(2008, 8, 11));

        when(bookService.updateBook(isbn, updatedBook)).thenReturn(updatedBook);

        mockMvc.perform(put("/api/books/{isbn}", isbn)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"978-3-16-148411-0\",\"title\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\"author\":\"Robert C. Martin\",\"publishDate\":\"2008-08-11\"}"))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeleteBook() throws Exception {
        String isbn = "978-3-16-148411-0";
        mockMvc.perform(delete("/api/books/{isbn}", isbn))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(isbn);
    }

}


