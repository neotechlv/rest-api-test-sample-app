package lv.schoollibrary.books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lv.schoollibrary.books.domain.Book;
import lv.schoollibrary.books.domain.EntityMapper;
import lv.schoollibrary.books.dtos.BookRequestDto;
import lv.schoollibrary.books.dtos.BookResponseDto;
import lv.schoollibrary.books.persistence.BookNotFoundException;
import lv.schoollibrary.books.persistence.BookRepository;

@Service
public class BooksService {

    private final BookRepository bookRepository;

    private final EntityMapper entityMapper;

    @Autowired
    public BooksService(BookRepository bookRepository, EntityMapper entityMapper) {
        this.bookRepository = bookRepository;
        this.entityMapper = entityMapper;
    }

    public BookResponseDto addItem(BookRequestDto item) {
        Book book = entityMapper.fromRequestDto(item);
        Book saved = bookRepository.save(book);
        return entityMapper.toResponseDto(saved);
    }

    public List<BookResponseDto> getAll() {
        return bookRepository.findAll().stream()
                .map(entityMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public BookResponseDto updateItem(String id, BookRequestDto item) {
        Book book = findById(id);
        book.setName(item.getName());
        book.setIsbn(item.getIsbn());
        Book saved = bookRepository.save(book);
        return entityMapper.toResponseDto(saved);
    }

    public void deleteItem(String id) {
        Book book = findById(id);
        bookRepository.delete(book);
    }

    public List<BookResponseDto> findByName(String name) {
        List<Book> books = bookRepository.findByName(name);
        return books.stream()
                .map(entityMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public BookResponseDto findByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        return entityMapper.toResponseDto(book);
    }

    private Book findById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}
