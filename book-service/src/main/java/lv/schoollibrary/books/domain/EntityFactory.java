package lv.schoollibrary.books.domain;

import lv.schoollibrary.books.dtos.BookResponseDto;

public class EntityFactory {

    public BookResponseDto createBookResponseDto() {
        return new BookResponseDto();
    }

    public Book createBook() {
        return new Book();
    }
}
