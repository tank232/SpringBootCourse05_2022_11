package com.av.dao;

import com.av.domain.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {


    Mono<Author> findAuthorByName(String name);

    Mono<Author> save(Mono<Author> author);
}
