package bookers.bookkeeper.book;

import bookers.bookkeeper.book.dto.BookDTO;
import bookers.bookkeeper.book.dto.BookDTOConverter;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController extends BaseController<Book, BookDTO, BookDTOConverter, BookService> {

    public BookController(BookService service, BookDTOConverter converter) {
        super(service, converter);
    }

    @PostMapping(value = "/authors")
    public List<BookDTO> getBooksByAuthor(@RequestBody List<Long> authorIds) {
        return converter.listToDto(service.getBooksByAuthorIds(authorIds));
    }

    @PostMapping(value = "/genres")
    public List<BookDTO> getBooksByGenre(@RequestBody List<String> genres) {
        return converter.listToDto(service.getBookByGenres(genres));
    }

    @GetMapping(value = "/author/{id}")
    public List<BookDTO> getBookByAuthor(@PathVariable(name = "id") Long authorId) {
        return converter.listToDto(service.getBookByAuthorId(authorId));
    }

}
