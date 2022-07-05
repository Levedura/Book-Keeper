package bookers.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRep;

    public List<BookEntity> getAllBooks(){
        return bookRep.findAll();
    }
}
