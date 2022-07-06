package bookers.bookkeeper.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRep;

    public List<Book> getAllBooks(){
        return bookRep.findAll();
    }

    public Book getBookById(Long id){
        return bookRep.findById(id).orElse(null);
    }

    @Autowired
    public BookService(BookRepository bookRep) {
        this.bookRep = bookRep;
    }
}
