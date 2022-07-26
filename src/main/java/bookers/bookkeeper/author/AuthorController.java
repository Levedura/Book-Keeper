package bookers.bookkeeper.author;

import bookers.bookkeeper.MapperBean;
import bookers.bookkeeper.author.dto.AuthorDTO;
import bookers.bookkeeper.author.dto.AuthorDTOConverter;
import bookers.bookkeeper.book.BookController;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/authors")
public class AuthorController extends BaseController<Author,AuthorDTO,AuthorRepository> {


    @Autowired
    public AuthorController(AuthorService authorService, AuthorDTOConverter authorConverter ) {
        super(authorService,authorConverter);
    }

/*

    @GetMapping("/author/{id}")
    public AuthorDTO getAuthorById(@PathVariable(name = "id") Long authorId) {
        AuthorDTO authorDto = authorConverter.toDto(authorService.getEntityById(authorId));
        authorService.getEntityById(authorId);
        Link test = linkTo(methodOn(BookController.class).getBookByAuthor(authorId)).withRel("books");

        authorDto.add(test);
        return authorDto;
    }

    @GetMapping("/authors/name&{pages}&{size}")
    public List<AuthorDTO> getAuthorsOrderedByName(@PathVariable Integer pages, @PathVariable Integer size) {
        return authorConverter.listToDto(authorService.getAuthorsOrderedByName(pages, size));
    }

    @GetMapping("/authors/favorites&{pages}&{size}")
    public List<AuthorDTO> getAuthorsOrderedByFavorites(@PathVariable Integer pages, @PathVariable Integer size) {
        return authorConverter.listToDto(authorService.getAuthorsOrderedByFavorites(pages, size));
    }

    @DeleteMapping("/author/{id}")
    public Long deleteAuthor(@PathVariable(name = "id") Long authorId) {
        return authorService.deleteEntityById(authorId);
    }
*/

}
