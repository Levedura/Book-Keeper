package bookers.bookkeeper.book;

import bookers.bookkeeper.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends BaseService<Book, BookRepository> {
    @Autowired
    public BookService(BookRepository bookRepository) {
        super(bookRepository);
    }
}
