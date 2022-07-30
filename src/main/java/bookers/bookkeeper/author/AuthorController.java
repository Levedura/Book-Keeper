package bookers.bookkeeper.author;

import bookers.bookkeeper.author.dto.AuthorDTO;
import bookers.bookkeeper.author.dto.AuthorDTOConverter;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController extends BaseController<Author, AuthorDTO, AuthorDTOConverter, AuthorService> {


    @Autowired
    public AuthorController(AuthorService authorService, AuthorDTOConverter authorConverter) {
        super(authorService, authorConverter);
    }
/*
    @GetMapping("/author/{id}")
    public AuthorDTO getAuthorById(@PathVariable(name = "id") Long authorId) {
        AuthorDTO authorDto = authorConverter.toDto(authorService.getEntityById(authorId));
        authorService.getEntityById(authorId);
        Link test = linkTo(methodOn(BookController.class).getBookByAuthor(authorId)).withRel("books");

        authorDto.add(test);
        return authorDto;
    }*/


}
