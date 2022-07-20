package bookers.bookkeeper.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    public Author getAuthorById(@PathVariable(name = "id") Long authorId){
        return authorService.findEntityById(authorId);
    }

    @PostMapping("/author")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.saveEntity(author);
    }

    @PostMapping("/authors")
    @Secured("USER")
    public List<Author> addListAuthors(@RequestBody List<Author> authors){
        return authorService.saveEntities(authors);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Long> deleteAuthor( @PathVariable(name = "id") Long authorId) {
        return authorService.deleteEntityById(authorId);
    }

}
