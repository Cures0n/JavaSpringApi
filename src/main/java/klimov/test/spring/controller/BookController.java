package klimov.test.spring.controller;

import klimov.test.spring.models.Book;
import klimov.test.spring.models.JsonResponse;
import klimov.test.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/title")
    public ResponseEntity<List<Book>> findAllOrderByTitle() {
            List<Book> book = bookRepository.findAllOrderByTitle();
            return new ResponseEntity<>(book, HttpStatus.OK);
    }

//    @GetMapping("/author")
//    public ResponseEntity<List<Book>> findAllGroupByAuthor() {
//            List<Book> book = bookRepository.findAllGroupByAuthor();
//            return new ResponseEntity<>(book, HttpStatus.OK);
//    }

    @GetMapping("/author")
    public ResponseEntity<Map<String, List<Book>>> findAllGroupByAuthor() {
        Map<String, List<Book>> booksByAuthor = bookRepository.findAll().stream().collect(Collectors.groupingBy(Book::getAuthor));

        return new ResponseEntity<>(booksByAuthor, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<JsonResponse> createBook(@RequestBody Book book) {
        bookRepository.save(new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription()));
        return new ResponseEntity<JsonResponse>(new JsonResponse("Book was added successfully."), HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<JsonResponse> authorAll(@RequestBody Book book) {
        bookRepository.save(new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription()));
        return new ResponseEntity<JsonResponse>(new JsonResponse("Book was added successfully."), HttpStatus.CREATED);
    }
}
