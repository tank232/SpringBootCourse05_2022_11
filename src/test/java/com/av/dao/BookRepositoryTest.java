package com.av.dao;


import com.av.domain.Book;
import com.av.domain.Comment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Test
    void findAll() {
        var book = new Book();
        book.setTitle("t");
        book.setIsbn("t");
        var a = bookRepository.save(book);
        assertNotNull(a.getTitle(), "book nor added");
        List<Book> all = bookRepository.findAll();
        assertEquals(1, all.size(), "book not fetched");
    }

    @Test
    void findBookByTitle() {
        var book = new Book();
        book.setTitle("t");
        book.setIsbn("t");
        var a = bookRepository.save(book);
        assertNotNull(a.getTitle(), "book nor added");
        var book1 = bookRepository.findBooksByTitle("t");
        assertEquals(1, book1.size(), "book not fetched");

    }

}