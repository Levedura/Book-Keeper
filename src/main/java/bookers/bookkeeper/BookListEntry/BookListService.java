package bookers.bookkeeper.BookListEntry;

import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.book.BookService;
import bookers.bookkeeper.user.User;
import bookers.bookkeeper.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookListService {

    private final UserService userService;
    private final BookService bookService;
    private final BookListRepository entryService;

    @Autowired
    public BookListService(UserService userService, BookService bookService, BookListRepository entryService) {
        this.userService = userService;
        this.bookService = bookService;
        this.entryService = entryService;
    }

    public List<BookEntry> getUserList(String username) {
        return userService.getUserByNameWithCheck(username).getUserbooks();
    }

    public BookEntry addBookEntry(BookEntryDTO bookEntryDto, String username) {
        BookEntry bookEntry = new BookEntry();
        Book bookToAdd = bookService.findById(bookEntryDto.getBookid());
        User user = userService.getUserByNameWithCheck(username);
        bookEntry.setBook(bookToAdd);
        bookEntry.setUser(user);
        bookEntry.setDateAdded(bookEntry.getDateAdded());
        bookEntry.setDateFinished(bookEntry.getDateFinished());
        bookEntry.setPagesRead(bookEntryDto.getPagesRead());
        bookEntry.setUserscore(bookEntryDto.getUserscore());
        bookEntry.setStatus(bookEntryDto.getStatus());

        user.getUserbooks().add(entryService.save(bookEntry));
        userService.save(user);
        return bookEntry;
    }

}
