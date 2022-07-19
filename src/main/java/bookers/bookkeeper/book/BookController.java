package bookers.bookkeeper.book;

import bookers.bookkeeper.enums.Genres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {

    BookService bs;

    @Autowired
    public BookController(BookService bs) {
        this.bs = bs;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bs.findAll();
    }

    @GetMapping(value = "/book/{id}")
    public Book getBookById(@PathVariable(name = "id") Long id) {
        return bs.findById(id);
    }

    @PostMapping(value = "/book")
    public Book addBook(@RequestBody Book book) {
        return bs.save(book);
    }

    @PostMapping(value = "/books")
    public List<Book> addMultipleBooks(@RequestBody List<Book> books) {
        return bs.saveAll(books);
    }
}
