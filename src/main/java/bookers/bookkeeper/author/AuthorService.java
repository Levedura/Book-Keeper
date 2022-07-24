package bookers.bookkeeper.author;

import bookers.bookkeeper.BaseService;
import bookers.bookkeeper.author.dto.AuthorDto;
import bookers.bookkeeper.author.dto.AuthorDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService extends BaseService<Author, AuthorRepository> {



    @Autowired
    public AuthorService(AuthorRepository rep ) {
        super(rep);
    }

    public List<Author> getAllAuthors() {
        return rep.findAll();
    }

    public Author getAuthor(Long id) {
        return findEntityById(id);
    }

    public List<Author> getAuthorsOrderedByName(int pages, int pageSize) {
        return getEntitiesOrderedBy(rep::findByOrderByNameAsc, pages, pageSize);
    }

    public List<Author> getAuthorsOrderedByFavorites(Integer pages, Integer pageSize) {
        return getEntitiesOrderedBy(rep::findByOrderByFavorites, pages, pageSize);
    }

    public Author addAuthor(Author author) {
        return rep.save(author);
    }

    public List<Author> addAuthors(List<Author> authors) {
        return rep.saveAll(authors);
    }

    public Long deleteAuthor(Long id) {
        return deleteEntityById(id);
    }

}
