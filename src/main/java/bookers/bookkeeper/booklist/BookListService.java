package bookers.bookkeeper.booklist;

import bookers.bookkeeper.generics.BaseService;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.book.BookService;
import bookers.bookkeeper.user.User;
import bookers.bookkeeper.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

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

    public BookEntry updateBookEntry(Long entryId, BookEntry bookEntry, String username) {

        User user = userService.getUserByNameWithCheck(username);
        BookEntry bookEntryFound = getEntityById(entryId);

        List<BookEntry> userList = user.getUserlist();
        int index = userList.indexOf(bookEntryFound);
        BookEntry bookEntryToChange = userList.get(index);

        bookEntryToChange.setPagesRead(bookEntry.getPagesRead());
        bookEntryToChange.setUserscore(bookEntry.getUserscore());
        bookEntryToChange.setStatus(bookEntry.getStatus());

        userService.saveUser(user);
        return user.getUserlist().get(index);
    }

    public BookEntry addBookEntry(BookEntry bookEntry, String username,Long bookId) {
        User user = userService.getUserByNameWithCheck(username);
        Book bookToAdd = bookService.getBook(bookId);
        if (userListContainsBook(user, bookToAdd)) {
            throw new IllegalStateException("Book already in list");
        }
        bookEntry.setBook(bookToAdd);
        bookEntry.setUser(user);
        rep.save(bookEntry);
        //userService.saveUser(user);
        return bookEntry;
    }


    public Long deleteEntry(Long id) {
        return super.deleteEntityById(id);
    }


    public List<BookEntry> getListSortedByUserScore(String username, Integer pages, Integer pageSize) {
        return getListSortedGeneric(username,pages,pageSize,rep::findByUserOrderByUserscore);
    }

    public List<BookEntry> getListSortedByDateAdded(String username, Integer pages, Integer pageSize) {
        return getListSortedGeneric(username,pages,pageSize,rep::findByUserOrderByDateAdded);
    }
    public List<BookEntry> getListSortedByDateFinished(String username, Integer pages, Integer pageSize) {
        return getListSortedGeneric(username,pages,pageSize,rep::findByUserOrderByDateFinished);
    }

    public List<BookEntry> getListSortedByPagesRead(String username, Integer pages, Integer pageSize) {
        return getListSortedGeneric(username,pages,pageSize,rep::findByUserOrderByPagesRead);
    }

    private List<BookEntry> getListSortedGeneric(String username, Integer pages, Integer pageSize, BiFunction<User,Pageable,List<BookEntry>> function){
        User user = userService.getUserByNameWithCheck(username);
        PageRequest pageReq = PageRequest.of(pages, pageSize);
        return function.apply(user,pageReq);
    }
}
