package bookers.bookkeeper.booklist;

import bookers.bookkeeper.BaseService;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.book.BookService;
import bookers.bookkeeper.bookentry.BookEntry;
import bookers.bookkeeper.bookentry.BookEntryDTO;
import bookers.bookkeeper.user.User;
import bookers.bookkeeper.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookListService extends BaseService<BookEntry, BookListRepository> {

    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public BookListService(UserService userService, BookService bookService, BookListRepository bookListService) {
        super(bookListService);
        this.userService = userService;
        this.bookService = bookService;
    }


    public List<BookEntry> getUserList(String username) {
        return userService.getUserByNameWithCheck(username).getUserlist();
    }

    public boolean userListContainsBook(User user, Book book) {
        for (BookEntry userbook : user.getUserlist()) {
            if (userbook.getBook().equals(book)) {
                return true;
            }
        }
        return false;

    }

    public BookEntry updateBookEntry(Long entryId, BookEntryDTO bookEntryDto, String username) {

        User user = userService.getUserByNameWithCheck(username);
        BookEntry bookEntry = findEntityById(entryId);

        List<BookEntry> userList = user.getUserlist();
        int index = userList.indexOf(bookEntry);
        BookEntry bookEntryToChange = userList.get(index);
        bookEntryToChange.setPagesRead(bookEntryDto.getPagesRead());
        bookEntryToChange.setUserscore(bookEntryDto.getUserscore());
        bookEntryToChange.setStatus(bookEntryDto.getStatus());
        userService.saveEntity(user);
        return user.getUserlist().get(index);
    }

    public BookEntry addBookEntry(BookEntryDTO bookEntryDto, String username) {
        BookEntry bookEntry = new BookEntry();
        Book bookToAdd = bookService.findEntityById(bookEntryDto.getBookid());
        User user = userService.getUserByNameWithCheck(username);

        if (userListContainsBook(user, bookToAdd)) {
            throw new IllegalStateException("Book already in list");
        }

        bookEntry.setBook(bookToAdd);
        bookEntry.setUser(user);
        bookEntry.setDateAdded(bookEntryDto.getDateAdded());
        bookEntry.setDateFinished(bookEntryDto.getDateFinished());
        bookEntry.setPagesRead(bookEntryDto.getPagesRead());
        bookEntry.setUserscore(bookEntryDto.getUserscore());
        bookEntry.setStatus(bookEntryDto.getStatus());

        user.getUserlist().add(saveEntity(bookEntry));

        //Might not be necessary
        userService.saveEntity(user);
        return bookEntry;
    }

    public Long deleteEntry(Long id) {
        return super.deleteEntityById(id);
    }


    public List<BookEntry> getListSortedByUserScore(String username, Integer pages, Integer pageSize) {
        User user = userService.getUserByNameWithCheck(username);
        return rep.findByUserOrderByDateAdded(user, PageRequest.of(pages,pageSize));
    }
}
