package bookers.bookkeeper.author;

import bookers.bookkeeper.author.dto.AuthorDTO;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController extends BaseController<Author, AuthorDTO, AuthorService, AuthorModelAssembler> {

    public AuthorController(AuthorService service, AuthorModelAssembler modelAssembler) {
        super(service, modelAssembler);
    }

    @GetMapping(value = "book/{bookId}")
    public CollectionModel<EntityModel<AuthorDTO>> getAuthorByBook(@PathVariable Long bookId) {
        return modelAssembler.toCollectionModel(service.findAuthorsByBooks(List.of(bookId)));
    }

}
