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
    public List<BookDTO> getBooksByAuthor(List<Long> authorIds) {
        return super.getEntitiesByList(authorIds, service::getBooksByAuthorIds);
    }

    @PostMapping(value = "/genres")
    public List<BookDTO> getBooksByGenre(List<String> genres) {
        return super.getEntitiesByList(genres, service::getBookByGenres);
    }

    @GetMapping(value = "/author/{id}")
    public List<BookDTO> getBookByAuthor(@PathVariable(name = "id") Long authorId) {
        return converter.listToDto(service.getBookByAuthorId(authorId));
    }

    @GetMapping(value = "/score")
    public List<BookDTO> getBooksOrderedByScore(Integer pages, Integer pageSize) {
        //Change this
        return super.getSimpleOrderedBy(pages, pageSize, service::getBooksOrderedByScore);
    }

    @GetMapping(value = "/npages")
    public List<BookDTO> getBooksOrderedByPages(Integer pages, Integer pageSize) {
        //Change this
        return super.getSimpleOrderedBy(pages, pageSize, service::getBooksOrderedByPages);
    }

    @GetMapping(value = "/title")
    public List<BookDTO> getBooksOrderedByTitle(Integer pages, Integer pageSize) {
        //Change this
        return super.getSimpleOrderedBy(pages, pageSize, service::getBooksOrderedByTitle);
    }


}
