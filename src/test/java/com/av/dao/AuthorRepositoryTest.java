package com.av.dao;

import com.av.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;



@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void findAll() {
        var author = new Author("fake2");
        var a = authorRepository.save(author);
        assertNotNull(a.getName(), "author nor added");
        var authorList = authorRepository.findAll();
        assertEquals(2, authorList.size(), "author not fetched");
    }

    @Test
    void findAuthorByName() {
        var author = new Author("fake2");
        var a = authorRepository.save(author);
        assertNotNull(a.getName(), "author nor added");
        var author1 = authorRepository.findAuthorByName("fake2");
        assertEquals(true, author1.isPresent(), "author not fetched");
    }


}