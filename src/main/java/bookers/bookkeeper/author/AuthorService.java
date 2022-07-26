package bookers.bookkeeper.author;

import bookers.bookkeeper.generics.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService extends BaseService<Author, AuthorRepository> {

    @Autowired
    public AuthorService(AuthorRepository authorRep) {
        super(authorRep);
    }

    public List<Author> getAuthorsOrderedByName(int pages, int pageSize) {

        return getEntitiesOrderedBy(rep::findByOrderByNameAsc, pages, pageSize);
    }

    public List<Author> getAuthorsOrderedByFavorites(Integer pages, Integer pageSize) {
        return getEntitiesOrderedBy(rep::findByOrderByFavorites, pages, pageSize);
    }


}
