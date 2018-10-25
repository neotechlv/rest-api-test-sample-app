package lv.schoollibrary.books.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lv.schoollibrary.books.dtos.BookRequestDto;
import lv.schoollibrary.books.dtos.BookResponseDto;
import lv.schoollibrary.books.dtos.ErrorDto;
import lv.schoollibrary.books.persistence.BookNotFoundException;
import lv.schoollibrary.books.services.BooksService;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@RequestMapping(path = "/api/v1/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BooksController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);

    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @ApiOperation(
            value = "Creates a new book")
    @ApiResponses({
            @ApiResponse(code = 200,
                    message = "Returned when the book is created successfully",
                    response = BookResponseDto.class
            ),
            @ApiResponse(code = 400,
                    message = "Returned when data is malformed",
                    response = ErrorDto.class)
    })
    @PostMapping
    public BookResponseDto add(@Valid @RequestBody BookRequestDto item) {
        LOGGER.debug("Adding book {}", item);
        return booksService.addItem(item);
    }

    @ApiOperation(value = "Gets all books")
    @GetMapping
    public List<BookResponseDto> allBooks(@ApiParam("Optional filter by book name") @RequestParam(required = false) String name) {
        if (!isBlank(name)) {
            LOGGER.debug("Retrieving all books with name '{}'", name);
            return booksService.findByName(name);
        } else {
            LOGGER.debug("Retrieving all books");
            return booksService.getAll();
        }
    }

    @ApiOperation(value = "Gets book by ISBN")
    @ApiResponses({
            @ApiResponse(code = 200,
                    message = "Returned when the book is found",
                    response = BookResponseDto.class
            ),
            @ApiResponse(code = 400,
                    message = "Returned when ISBN is malformed or book not found (distinguished by error code)",
                    response = ErrorDto.class)
    })
    @GetMapping("/{isbn}")
    public BookResponseDto getBookByIsbn(@PathVariable String isbn) {
        LOGGER.debug("Get book by ISBN {}", isbn);
        return booksService.findByIsbn(isbn);
    }

    @ApiOperation(
            value = "Updates book info")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned when the book is updated successfully", response = BookResponseDto.class),
            @ApiResponse(code = 400, message = "Returned when data is malformed"),
    })
    @PutMapping("/{id}")
    public BookResponseDto update(@PathVariable String id, @Valid @RequestBody BookRequestDto item) {
        LOGGER.debug("Updating book {} to be {}", id, item);
        return booksService.updateItem(id, item);
    }

    @ApiOperation(
            value = "Deletes a book")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned when the book is deleted successfully"),
            @ApiResponse(code = 400, message = "Returned when the book doesn't exist"),
    })
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        LOGGER.debug("Deleting book {}", id);
        booksService.deleteItem(id);
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorDto handleBookNotFoundException(BookNotFoundException ex) {
        return new ErrorDto.Builder()
                .code(ErrorDto.NOT_FOUND_ERROR_CODE)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Request processing failed")
    public void handleGeneralError(Exception ex) {
        LOGGER.error("Request processing failed", ex);
    }

}
