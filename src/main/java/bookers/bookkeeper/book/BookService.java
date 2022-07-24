package bookers.bookkeeper.book;

import bookers.bookkeeper.BaseService;
import bookers.bookkeeper.book.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends BaseService<Book, BookRepository> {

    @Autowired
    public BookService(BookRepository bookRepository) {
        super(bookRepository);
    }


    public List<Book> getAllBooks(){
        return rep.findAll();
    }

    public List<Book> getBooksOrderedByScore(Integer pages, Integer pageSize) {
        return getEntitiesOrderedBy(rep::findByOrderByGlobalscore,pages,pageSize);
    }

    public List<Book> getBooksOrderedByTitle(Integer pages, Integer pageSize) {
        return getEntitiesOrderedBy(rep::findByOrderByTitle,pages,pageSize);
    }

    public Book addBook(Book book){
        return rep.save(book);
    }

    public List<Book> addBooks(List<Book> books){
        return rep.saveAll(books);
    }

    public List<Book> getBooksOrderedByPages(Integer pages, Integer pageSize) {
        return getEntitiesOrderedBy(rep::findByOrderByPages,pages,pageSize);
    }

    public Book getBook(Long bookId) {
        return findEntityById(bookId);
    }
}
