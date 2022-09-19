package com.av.dao;

import com.av.domain.Author;
import com.av.domain.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {


    Mono<Book> findBooksByTitle(String title);

    Flux<Book> findBooksByAuthors(String author);

    Mono<Book> save(Mono<Book> book);


}
