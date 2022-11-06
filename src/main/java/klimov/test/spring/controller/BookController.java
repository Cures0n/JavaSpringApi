package klimov.test.spring.controller;

import klimov.test.spring.models.Book;
import klimov.test.spring.models.JsonResponse;
import klimov.test.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/author")
    public ResponseEntity<Map<String, List<Book>>> findAllGroupByAuthor() {
        Map<String, List<Book>> booksByAuthor = bookRepository.findAll().stream().collect(Collectors.groupingBy(Book::getAuthor));
        return new ResponseEntity<>(booksByAuthor, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<JsonResponse> createBook(@RequestBody Book book) {
        bookRepository.save(new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription()));
        return new ResponseEntity<>(new JsonResponse("Book was added successfully."), HttpStatus.CREATED);
    }

//    @PostMapping("/count")
//    public ResponseEntity<List<Book>> authorAll(@RequestBody String symbol) {
//        List<Book> booksByAuthor = bookRepository.findAll();
//        for (int i = 0; i <= booksByAuthor.size(); i++){
//            long count_symbol = booksByAuthor.get(i).getTitle().codePoints().filter(ch -> ch == symbol).count();
//            System.out.println(count_symbol);
//        }
//        return new ResponseEntity<>(booksByAuthor, HttpStatus.OK);
//    }
}
