package bookers.bookkeeper.author;

import bookers.bookkeeper.MapperBean;
import bookers.bookkeeper.author.dto.AuthorDto;
import bookers.bookkeeper.author.dto.AuthorDtoConverter;
import bookers.bookkeeper.book.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorDtoConverter authorConverter;
    private final MapperBean dtoMapper;

    @Autowired
    public AuthorController(AuthorService authorService, AuthorDtoConverter authorConverter, MapperBean dtoMapper) {
        this.authorService = authorService;
        this.authorConverter = authorConverter;
        this.dtoMapper = dtoMapper;
    }


    @GetMapping("/authors")
    public List<AuthorDto> getAuthors() {
        return authorConverter.listToDto(authorService.getAllAuthors());
    }

    @GetMapping("/author/{id}")
    public AuthorDto getAuthorById(@PathVariable(name = "id") Long authorId) {
        AuthorDto authorDto = authorConverter.toDto(authorService.getAuthor(authorId));
        Link test = linkTo(methodOn(BookController.class).getBookByAuthor(authorId)).withRel("books");
        authorDto.add(test);
        return authorDto;
    }

    @GetMapping("/authors/name&{pages}&{size}")
    public List<AuthorDto> getAuthorsOrderedByName(@PathVariable Integer pages, @PathVariable Integer size) {
        return authorConverter.listToDto(authorService.getAuthorsOrderedByName(pages, size));
    }

    @GetMapping("/authors/favorites&{pages}&{size}")
    public List<AuthorDto> getAuthorsOrderedByFavorites(@PathVariable Integer pages, @PathVariable Integer size) {
        return authorConverter.listToDto(authorService.getAuthorsOrderedByFavorites(pages, size));
    }

    @DeleteMapping("/author/{id}")
    public Long deleteAuthor(@PathVariable(name = "id") Long authorId) {
        return authorService.deleteAuthor(authorId);
    }

}
