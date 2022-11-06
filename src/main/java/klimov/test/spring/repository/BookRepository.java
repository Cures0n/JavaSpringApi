package klimov.test.spring.repository;

import klimov.test.spring.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BookRepository {
    int save(Book book);

    List<Book> findAllOrderByTitle();

//    List<Book> findAllGroupByAuthor();

    List<Book> findAll();

    List<Book> findByAuthor(String author);
}
