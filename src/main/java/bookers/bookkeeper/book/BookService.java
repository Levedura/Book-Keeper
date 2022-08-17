package bookers.bookkeeper.book;

import bookers.bookkeeper.book.dto.BookSimpleView;
import bookers.bookkeeper.generics.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static bookers.bookkeeper.book.BookSpecs.authorHasName;
import static bookers.bookkeeper.book.BookSpecs.hasTitle;

@Service
public class BookService extends BaseService<Book, BookRepository> {

    @Autowired
    public BookService(BookRepository bookRepository) {
        super(bookRepository);
    }

    List<BookSimpleView> a() {
        return rep.getBooksBy();
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


    public Page<Book> searchByTitle(String words) {
        Pageable page = PageRequest.of(0, 10);
        System.out.println(words);
        return rep.findAll(hasTitle(words).or(authorHasName("Pyotr")), page);
    }

}
