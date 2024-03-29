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


    List<Author> findAuthorsByBooks(List<Long> bookIds) {
        return rep.findAuthorsByBooks(bookIds);
    }


}
