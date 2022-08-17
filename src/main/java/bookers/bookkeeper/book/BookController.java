package bookers.bookkeeper.book;

import bookers.bookkeeper.author.AuthorModelAssembler;
import bookers.bookkeeper.author.AuthorRepository;
import bookers.bookkeeper.author.dto.AuthorDTO;
import bookers.bookkeeper.book.dto.BookDTO;
import bookers.bookkeeper.book.dto.BookSimpleView;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController extends BaseController<Book, BookDTO, BookService, BookModelAssembler> {

    AuthorRepository authorService;
    AuthorModelAssembler authorModelAssembler;

    public BookController(BookService service, BookModelAssembler modelAssembler, AuthorRepository authorService, AuthorModelAssembler authorModelAssembler) {
        super(service, modelAssembler);
        this.authorService = authorService;
        this.authorModelAssembler = authorModelAssembler;
    }

    @GetMapping(value = "/simple")
    public List<BookSimpleView> getSimpleBooks() {
        return service.a();
    }

    @GetMapping(value = "{id}/authors")
    public CollectionModel<EntityModel<AuthorDTO>> getBookAuthors(@PathVariable(name = "id") Long bookId) {
        return authorModelAssembler.toCollectionModel(authorService.findAuthorsByBooks(List.of(bookId)));
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

    @GetMapping(value = "/s/")
    public CollectionModel<EntityModel<BookDTO>> searchBookByTitle(@RequestBody Map<String, String> search) {
        return modelAssembler.toCollectionModel(service.searchByTitle(search.get("search")));
    }
}
