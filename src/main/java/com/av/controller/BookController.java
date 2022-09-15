package com.av.controller;

import com.av.dao.AuthorRepository;
import com.av.dao.BookRepository;
import com.av.domain.Author;
import com.av.domain.Book;
import com.av.domain.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    @PostMapping("/book")
    public  Mono<Book> saveBook(@RequestBody Mono<Book> dto) {
        return  bookRepository.save(dto);
    }

    @GetMapping("/book")
    public Flux<Book> allBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/title")
    public Mono<Book> getBook(@PathVariable("title") String title) {
        return bookRepository.findBooksByTitle(title);
    }

    @PostMapping("/author")
    public Mono<Author> saveAuthor(@RequestBody Mono<Author> dto) {
        return   authorRepository.save(dto);
    }

    @GetMapping("/author")
    public Flux<Author> allAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping("/author/name/")
    public Mono<ResponseEntity<Void>> update_author_name(@RequestParam(value = "old")  String oldName, @RequestParam(value = "new")  String newName) {
        return  authorRepository.findAuthorByName(oldName).flatMap(
                author->
                {
                    Flux<Book> booksByAuthor = bookRepository.findBooksByAuthors(oldName);
                    author.setName(newName);
                    authorRepository.save(author);
                    booksByAuthor.flatMap(
                            book->{
                                book.getAuthors().add(newName);
                                book.getAuthors().remove(oldName);
                                return bookRepository.save(book);
                            }
                    );
                    return authorRepository.delete(author).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
                }
        ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
