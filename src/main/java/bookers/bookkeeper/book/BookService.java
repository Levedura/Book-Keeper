package bookers.bookkeeper.book;

import bookers.bookkeeper.generics.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService extends BaseService<Book, BookRepository> {

    @Autowired
    public BookService(BookRepository bookRepository) {
        super(bookRepository);
    }

    public List<Book> getBooksByAuthorIds(List<Long> authors) {
        return rep.findByAuthors(authors);
    }

    public List<Book> getBookByAuthorId(Long authorId) {
        return rep.findByAuthors(List.of(authorId));
    }

    public Book getBook(Long bookId) {
        return getEntityById(bookId);
    }

    public List<Book> getBookByGenres(List<String> genres) {
        return rep.findByGenres(genres, genres.size()).stream().map(super::getEntityById).collect(Collectors.toList());
    }

}
