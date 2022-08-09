package bookers.bookkeeper.book;

import bookers.bookkeeper.book.dto.BookDTO;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController extends BaseController<Book, BookDTO, BookService, BookModelAssembler> {

    public BookController(BookService service, BookModelAssembler modelAssembler) {
        super(service, modelAssembler);
    }

    @PostMapping(value = "/authors")
    public CollectionModel<EntityModel<BookDTO>> getBooksByAuthor(@RequestBody List<Long> authorIds) {
        return modelAssembler.toCollectionModel(service.getBooksByAuthorIds(authorIds));
    }

    @PostMapping(value = "/genres")
    public CollectionModel<EntityModel<BookDTO>> getBooksByGenre(@RequestBody List<String> genres) {
        return modelAssembler.toCollectionModel(service.getBookByGenres(genres));
    }

    @GetMapping(value = "/author/{id}")
    public CollectionModel<EntityModel<BookDTO>> getBookByAuthor(@PathVariable(name = "id") Long authorId) {
        return modelAssembler.toCollectionModel(service.getBookByAuthorId(authorId));
    }

}
