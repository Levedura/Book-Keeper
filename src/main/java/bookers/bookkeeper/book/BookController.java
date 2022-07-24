package bookers.bookkeeper.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {


    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getAllEntities();
    }

    @GetMapping(value = "/book/{id}")
    public Book getBookById(@PathVariable(name = "id") Long bookId) {
        return bookService.findEntityById(bookId);
    }

    @GetMapping(value = "/books/score&{pages}&{pageSize}")
    public List<Book> getBooksOrderedByScore(@PathVariable Integer pages, @PathVariable Integer pageSize) {
        return bookService.getBooksOrderedByScore(pages,pageSize);
    }

    @GetMapping(value = "/books/pages&{pages}&{pageSize}")
    public List<Book> getBooksOrderedByPages(@PathVariable Integer pages, @PathVariable Integer pageSize) {
        return bookService.getBooksOrderedByPages(pages,pageSize);
    }

    @GetMapping(value = "/books/title&{pages}&{pageSize}")
    public List<Book> getBooksOrderedByTitle(@PathVariable Integer pages, @PathVariable Integer pageSize) {
        return bookService.getBooksOrderedByTitle(pages,pageSize);
    }

    @PostMapping(value = "/book")
    public Book addBook(@RequestBody Book book) {
        return bookService.(book);
    }

    @PostMapping(value = "/books")
    public List<Book> addMultipleBooks(@RequestBody List<Book> books) {
        return bookService.saveEntities(books);
    }


}
