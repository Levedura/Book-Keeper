package bookers.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bs;

    @RequestMapping("/books")
    public List<BookEntity> getBooks(){
        return bs.getAllBooks();
    }

}
