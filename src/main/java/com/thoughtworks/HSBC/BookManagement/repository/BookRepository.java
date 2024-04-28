package com.thoughtworks.HSBC.BookManagement.repository;

import com.thoughtworks.HSBC.BookManagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

}

