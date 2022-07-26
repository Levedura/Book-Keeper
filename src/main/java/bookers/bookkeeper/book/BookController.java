package bookers.bookkeeper.book;

import bookers.bookkeeper.author.dto.AuthorDTOConverter;
import bookers.bookkeeper.book.dto.BookDTO;
import bookers.bookkeeper.book.dto.BookDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {


    BookService bookService;
    BookDTOConverter bookConverter;
    AuthorDTOConverter authorConverter;

    @Autowired
    public BookController(BookService bookService, BookDTOConverter bookConverter, AuthorDTOConverter authorConverter) {
        this.bookService = bookService;
        this.bookConverter = bookConverter;
        this.authorConverter = authorConverter;
    }


    @GetMapping("/books")
    public List<BookDTO> getBooks() {
        return bookConverter.listToDto(bookService.getAllEntities());
    }

    @GetMapping(value = "/book/{id}")
    public BookDTO getBookById(@PathVariable(name = "id") Long bookId) {
        return bookConverter.toDto(bookService.getBook(bookId));
    }

    @PostMapping(value = "/booksByAuthors")
    public List<BookDTO> getBooksByAuthor(@RequestBody List<Long> authorIds) {
        return bookConverter.listToDto(bookService.getBooksByAuthorIds(authorIds));
    }

    @PostMapping(value = "/books/genres")
    public List<BookDTO> getBooksByGenre(@RequestBody List<String> genres) {
        return bookConverter.listToDto(bookService.getBookByGenres(genres));
    }

    @GetMapping(value = "/booksByAuthor/{id}")
    public List<BookDTO> getBookByAuthor(@PathVariable(name = "id") Long authorId) {
        return bookConverter.listToDto(bookService.getBookByAuthorId(authorId));
    }

    @GetMapping(value = "/books/score&{pages}&{pageSize}")
    public List<BookDTO> getBooksOrderedByScore(@PathVariable Integer pages, @PathVariable Integer pageSize) {
        return bookConverter.listToDto(bookService.getBooksOrderedByScore(pages, pageSize));
    }

    @GetMapping(value = "/books/pages&{pages}&{pageSize}")
    public List<BookDTO> getBooksOrderedByPages(@PathVariable Integer pages, @PathVariable Integer pageSize) {
        return bookConverter.listToDto(bookService.getBooksOrderedByPages(pages, pageSize));
    }

    @GetMapping(value = "/books/title&{pages}&{pageSize}")
    public List<BookDTO> getBooksOrderedByTitle(@PathVariable Integer pages, @PathVariable Integer pageSize) {
        return bookConverter.listToDto(bookService.getBooksOrderedByTitle(pages, pageSize));
    }

    @PostMapping(value = "/book")
    public BookDTO addBook(@RequestBody BookDTO book) {
        return bookConverter.toDto(bookService.addBook(bookConverter.fromDto(book)));
    }

    @PostMapping(value = "/books")
    public List<BookDTO> addBooks(@RequestBody List<BookDTO> books) {
        return bookConverter.listToDto(bookService.addBooks(bookConverter.listFromDto(books)));


    }


}
