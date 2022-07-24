package bookers.bookkeeper.author;

import bookers.bookkeeper.author.dto.AuthorDto;
import bookers.bookkeeper.author.dto.AuthorDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorDtoConverter authorConverter;
    @Autowired
    public AuthorController(AuthorService authorService, AuthorDtoConverter authorConverter) {
        this.authorService = authorService;
        this.authorConverter = authorConverter;
    }


    @GetMapping("/authors")
    public List<AuthorDto> getAuthors() {
        return authorConverter.listToDto(authorService.getAllAuthors());
    }

    @GetMapping("/author/{id}")
    public AuthorDto getAuthorById(@PathVariable(name = "id") Long authorId) {
        return authorConverter.toDto(authorService.getAuthor(authorId));
    }

    @GetMapping("/authors/name&{pages}&{size}")
    public List<AuthorDto> getAuthorsOrderedByName(@PathVariable Integer pages, @PathVariable Integer size) {
        return authorConverter.listToDto(authorService.getAuthorsOrderedByName(pages, size));
    }

    @GetMapping("/authors/favorites&{pages}&{size}")
    public List<AuthorDto> getAuthorsOrderedByFavorites(@PathVariable Integer pages, @PathVariable Integer size) {
        return authorConverter.listToDto(authorService.getAuthorsOrderedByFavorites(pages, size));
    }

    @PostMapping("/author")
    public AuthorDto addAuthor(@RequestBody AuthorDto author) {
        return authorConverter.toDto(authorService.addAuthor(authorConverter.fromDto(author)));
    }

    @PostMapping("/authors")
    public List<AuthorDto> addListAuthors(@RequestBody List<AuthorDto> authors) {
        return authorConverter.listToDto(authorService.addAuthors(authorConverter.listFromDto(authors)));
    }

    @DeleteMapping("/author/{id}")
    public Long deleteAuthor(@PathVariable(name = "id") Long authorId) {
        return authorService.deleteAuthor(authorId);
    }

}
