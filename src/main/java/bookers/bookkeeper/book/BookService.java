package bookers.bookkeeper.book;

import bookers.bookkeeper.BaseService;
import bookers.bookkeeper.enums.Genres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService extends BaseService<Book, BookRepository> {

    @Autowired
    public BookService(BookRepository bookRepository) {
        super(bookRepository);
    }


    public List<Book> getAllBooks() {
        return rep.findAll();
    }

    public List<Book> getBooksOrderedByScore(Integer pages, Integer pageSize) {
        return getEntitiesOrderedBy(rep::findByOrderByGlobalscore, pages, pageSize);
    }

    public List<Book> getBooksOrderedByTitle(Integer pages, Integer pageSize) {
        return getEntitiesOrderedBy(rep::findByOrderByTitle, pages, pageSize);
    }

    public Book addBook(Book book) {
        return rep.save(book);
    }

    public List<Book> addBooks(List<Book> books) {
        return rep.saveAll(books);
    }

    public List<Book> getBooksOrderedByPages(Integer pages, Integer pageSize) {
        return getEntitiesOrderedBy(rep::findByOrderByPages, pages, pageSize);
    }

    public List<Book> getBooksByAuthorIds(List<Long> authors) {
        return rep.findByAuthors(authors);
    }

    public List<Book> getBookByAuthorId(Long authorId) {
        return rep.findByAuthors(List.of(authorId));
    }

    public Book getBook(Long bookId) {
        return findEntityById(bookId);
    }

    public List<Book> getBookByGenres(List<String> genres) {
        //Change to sql if possible
        return rep.findByGenres(genres,genres.size()).stream().map(super::findEntityById).collect(Collectors.toList());
    }
}
