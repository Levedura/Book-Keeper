package bookers.bookkeeper.author;

import bookers.bookkeeper.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends BaseService<Author, AuthorRepository> {

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        super(authorRepository);
    }

}
