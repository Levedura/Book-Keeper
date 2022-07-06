package bookers.bookkeeper.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return bs.getAllBooks();
    }

    @GetMapping(value = "/books/{id}")
    public Book getBookById(@PathVariable(name = "id") Long id){
        return bs.getBookById(id);
    }

}
