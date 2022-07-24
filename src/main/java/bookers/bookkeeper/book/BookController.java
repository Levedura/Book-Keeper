package bookers.bookkeeper.book;

import bookers.bookkeeper.book.dto.BookDto;
import bookers.bookkeeper.book.dto.BookDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {


    BookService bookService;
    BookDtoConverter bookConverter;

    @Autowired
    public BookController(BookService bookService, BookDtoConverter bookConverter) {
        this.bookService = bookService;
        this.bookConverter = bookConverter;
    }

    @GetMapping("/books")
    public List<BookDto> getBooks() {
        return bookConverter.listToDto(bookService.getAllBooks());
    }

    @GetMapping(value = "/book/{id}")
    public BookDto getBookById(@PathVariable(name = "id") Long bookId) {
        return bookConverter.toDto(bookService.getBook(bookId));
    }

    @GetMapping(value = "/books/score&{pages}&{pageSize}")
    public List<BookDto> getBooksOrderedByScore(@PathVariable Integer pages, @PathVariable Integer pageSize) {
        return bookConverter.listToDto(bookService.getBooksOrderedByScore(pages, pageSize));
    }

    @GetMapping(value = "/books/pages&{pages}&{pageSize}")
    public List<BookDto> getBooksOrderedByPages(@PathVariable Integer pages, @PathVariable Integer pageSize) {
        return bookConverter.listToDto(bookService.getBooksOrderedByPages(pages, pageSize));
    }

    @GetMapping(value = "/books/title&{pages}&{pageSize}")
    public List<BookDto> getBooksOrderedByTitle(@PathVariable Integer pages, @PathVariable Integer pageSize) {
        return bookConverter.listToDto(bookService.getBooksOrderedByTitle(pages,pageSize));
    }

    @PostMapping(value = "/book")
    public BookDto addBook(@RequestBody BookDto book) {
        return bookConverter.toDto(bookService.addBook(bookConverter.fromDto(book)));
    }

    @PostMapping(value = "/books")
    public List<BookDto> addBooks(@RequestBody List<BookDto> books) {
        return bookConverter.listToDto(bookService.addBooks(bookConverter.listFromDto(books)));
    }


}
