package bookers.bookkeeper.booklist;

import bookers.bookkeeper.BaseService;
import bookers.bookkeeper.book.BookService;
import bookers.bookkeeper.bookentry.BookEntry;
import bookers.bookkeeper.bookentry.BookEntryDTO;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.user.User;
import bookers.bookkeeper.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookListService extends BaseService<BookEntry,BookListRepository> {

    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public BookListService(UserService userService, BookService bookService, BookListRepository bookListService) {
        super(bookListService);
        this.userService = userService;
        this.bookService= bookService;
    }

    public List<BookEntry> getUserList(String username) {
        return userService.getUserByNameWithCheck(username).getUserlist();
    }
    public boolean userListContainsBook(User user,Book book){
        for (BookEntry userbook : user.getUserlist()) {
            if(userbook.getBook().equals(book)){
                return true;
            }
        }
        return false;

    }
    public BookEntry updateBookEntry(Long entryId, BookEntryDTO bookEntryDto, String username){

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

        if(userListContainsBook(user,bookToAdd)){
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

        userService.saveEntity(user);
        return bookEntry;
    }

    public ResponseEntity<Long> deleteEntry(Long id) {
        return super.deleteEntityById(id);
    }
}