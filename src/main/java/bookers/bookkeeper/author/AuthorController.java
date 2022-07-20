package bookers.bookkeeper.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Author> getAuthors() {
        return authorService.getAllEntities();
    }

    @GetMapping("/author/{id}")
    public Author getAuthorById(@PathVariable(name = "id") Long authorId) {
        return authorService.findEntityById(authorId);
    }

    @GetMapping("/authors/name&{pages}&{size}")
    public List<Author> getAuthorsOrderedByName(@PathVariable Integer pages, @PathVariable Integer size) {
        return authorService.getAuthorsOrderedByName(pages, size);
    }

    @GetMapping("/authors/favorites&{pages}&{size}")
    public List<Author> getAuthorsOrderedByFavorites(@PathVariable Integer pages, @PathVariable Integer size) {
        return authorService.getAuthorsOrderedByFavorites(pages, size);
    }
    @PostMapping("/author")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.saveEntity(author);
    }

    @PostMapping("/authors")
    public List<Author> addListAuthors(@RequestBody List<Author> authors) {
        return authorService.saveEntities(authors);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Long> deleteAuthor(@PathVariable(name = "id") Long authorId) {
        return authorService.deleteEntityById(authorId);
    }

}
