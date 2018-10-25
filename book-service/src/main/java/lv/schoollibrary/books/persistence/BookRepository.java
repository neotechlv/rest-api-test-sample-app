package lv.schoollibrary.books.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import lv.schoollibrary.books.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByName(String name);

    Book findByIsbn(String isbn);

}
