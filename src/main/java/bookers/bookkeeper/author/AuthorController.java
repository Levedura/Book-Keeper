package bookers.bookkeeper.author;

import bookers.bookkeeper.author.dto.AuthorDTO;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController extends BaseController<Author, AuthorDTO, AuthorService, AuthorModelAssembler> {

    public AuthorController(AuthorService service, AuthorModelAssembler modelAssembler) {
        super(service, modelAssembler);
    }
}
