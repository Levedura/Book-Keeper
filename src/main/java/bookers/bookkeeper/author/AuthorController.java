package bookers.bookkeeper.author;

import bookers.bookkeeper.author.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/authors")
    public List<AuthorDto> getAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/author/{id}")
    public AuthorDto getAuthorById(@PathVariable(name = "id") Long authorId) {
        return authorService.getAuthor(authorId);
    }

    @GetMapping("/authors/name&{pages}&{size}")
    public List<AuthorDto> getAuthorsOrderedByName(@PathVariable Integer pages, @PathVariable Integer size) {
        return authorService.getAuthorsOrderedByName(pages, size);
    }

    @GetMapping("/authors/favorites&{pages}&{size}")
    public List<AuthorDto> getAuthorsOrderedByFavorites(@PathVariable Integer pages, @PathVariable Integer size) {
        return authorService.getAuthorsOrderedByFavorites(pages, size);
    }

    @PostMapping("/author")
    public AuthorDto addAuthor(@RequestBody AuthorDto author) {
        return authorService.addAuthor(author);
    }

    @PostMapping("/authors")
    public List<AuthorDto> addListAuthors(@RequestBody List<AuthorDto> authors) {
        return authorService.addAuthors(authors);
    }

    @DeleteMapping("/author/{id}")
    public Long deleteAuthor(@PathVariable(name = "id") Long authorId) {
        return authorService.deleteEntityById(authorId);
    }

}
