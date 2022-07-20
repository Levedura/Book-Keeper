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

    @PostMapping(value = "/book")
    public Book addBook(@RequestBody Book book) {
        return bookService.saveEntity(book);
    }

    @PostMapping(value = "/books")
    public List<Book> addMultipleBooks(@RequestBody List<Book> books) {
        return bookService.saveEntities(books);
    }
}
