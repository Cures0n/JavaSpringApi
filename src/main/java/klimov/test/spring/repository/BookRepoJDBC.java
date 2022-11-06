package klimov.test.spring.repository;

import klimov.test.spring.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepoJDBC implements BookRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO  book (id, title, author, description) VALUES (?,?,?,?)",
                new Object[] { book.getId(), book.getTitle(), book.getAuthor(), book.getDescription() });
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * from book", BeanPropertyRowMapper.newInstance(Book.class));
    }

    @Override
    public List<Book> findAllOrderByTitle() {
        return jdbcTemplate.query("SELECT * from book ORDER BY title DESC", BeanPropertyRowMapper.newInstance(Book.class));
    }

//    @Override
//    public List<Book> findAllGroupByAuthor() {
//        return jdbcTemplate.query("SELECT id,author, title, description from book GROUP BY author, title, id, description", BeanPropertyRowMapper.newInstance(Book.class));
//    }

    @Override
    public List<Book> findByAuthor(String author) {
        return jdbcTemplate.query("SELECT * from book WHERE author=?",
                BeanPropertyRowMapper.newInstance(Book.class), author);
    }

}
