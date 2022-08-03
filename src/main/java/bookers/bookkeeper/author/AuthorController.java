package bookers.bookkeeper.author;

import bookers.bookkeeper.author.dto.AuthorDTO;
import bookers.bookkeeper.author.dto.AuthorDTOConverter;
import bookers.bookkeeper.book.BookController;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/authors")
public class AuthorController extends BaseController<Author, AuthorDTO, AuthorDTOConverter, AuthorService> {


    @Autowired
    public AuthorController(AuthorService authorService, AuthorDTOConverter authorConverter) {
        super(authorService, authorConverter);
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable(name = "id") Long authorId) {
        AuthorDTO authorDto = converter.toDto(service.getEntityById(authorId));
        service.getEntityById(authorId);
        Link test = linkTo(methodOn(BookController.class).getBookByAuthor(authorId)).withRel("books");
        authorDto.add(test);
        return authorDto;
    }


}
