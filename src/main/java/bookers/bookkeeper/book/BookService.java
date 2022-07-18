package bookers.bookkeeper.book;

import bookers.bookkeeper.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService extends BaseService<Book,BookRepository> {

    @Autowired
    public BookService(BookRepository bookRep) {
        super(bookRep);
    }
}
