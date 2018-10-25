package lv.schoollibrary.books.persistence;

import static java.lang.String.format;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String id) {
        super(format("Book %s not found", id));
    }
}
