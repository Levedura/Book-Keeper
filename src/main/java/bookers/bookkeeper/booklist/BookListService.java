package bookers.bookkeeper.booklist;

import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.book.BookService;
import bookers.bookkeeper.booklist.dto.AddEntryDTO;
import bookers.bookkeeper.enums.Status;
import bookers.bookkeeper.generics.BaseService;
import bookers.bookkeeper.user.User;
import bookers.bookkeeper.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public BookEntry updateBookEntry(Long entryId, Map<String, Object> bookEntry) {
        BookEntry bookEntryFound = getEntityById(entryId);
        bookEntry.forEach((getterName, updateValue) -> {
            Field field = ReflectionUtils.findField(BookEntry.class, getterName);
            field.setAccessible(true);
            ReflectionUtils.setField(field, bookEntryFound, updateValue);
        });
        return rep.save(bookEntryFound);
    }

    public BookEntry addBookEntry(AddEntryDTO addEntryDTO, String username) {
        User user = userService.getUserByNameWithCheck(username);
        Book bookToAdd = bookService.getBook(addEntryDTO.getBookId());
        BookEntry bookEntry = new BookEntry();
        if (getUserListBooks(username).contains(bookToAdd)) {
            throw new IllegalStateException("Book already in list");
        }
        bookEntry.setBook(bookToAdd);
        bookEntry.setUser(user);
        bookEntry.setDateAdded(addEntryDTO.getDateAdded());
        bookEntry.setDateFinished(addEntryDTO.getDateFinished());
        bookEntry.setNotes(addEntryDTO.getNotes());
        bookEntry.setStatus(addEntryDTO.getStatus());
        bookEntry.setPagesRead(addEntryDTO.getPagesRead());
        bookEntry.setUserScore(addEntryDTO.getUserScore());

        return rep.save(bookEntry);
    }


    public Long deleteEntry(Long id) {
        return super.deleteEntityById(id);
    }


    private List<Book> getUserListBooks(String username) {
        return getUserList(username).stream().map(BookEntry::getBook).collect(Collectors.toList());
    }

    public List<BookEntry> getUserListSorted(String username, String sort, Integer pages, Integer pageSize) {
        Pageable page = PageRequest.of(pages, pageSize, Sort.by(sort));
        return rep.findBookEntriesByUser_Username(username, page);
    }

    public List<BookEntry> getUserListByStatus(String username, Status status ){
        return rep.findBookEntriesByUser_UsernameAndStatus(username,status);

    }
}
