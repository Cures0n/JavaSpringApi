package klimov.test.spring.repository;

import klimov.test.spring.models.Book;

import java.util.List;

public interface BookRepository {
    int save(Book book);

    List<Book> findAllOrderByTitle();

    List<Book> findAll();

}
