package bookers.bookkeeper.author;

import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthors() {
        List<Author> list = authorService.findAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    
    @GetMapping("/author/{id}")
    public Author getAuthorById(@PathVariable(name = "id") Long authorId){
        return authorService.findById(authorId);
    }

    @PostMapping("/author")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PostMapping("/authors")
    @Secured("USER")
    public List<Author> addListAuthors(@RequestBody List<Author> authors){
        return authorService.saveAll(authors);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Long> deleteAuthor( @PathVariable(name = "id") Long authorId) {
        return authorService.deleteById(authorId);
    }

}
