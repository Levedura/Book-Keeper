package bookers.bookkeeper.booklist;

import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.book.BookService;
import bookers.bookkeeper.booklist.dto.AddEntryDTO;
import bookers.bookkeeper.enums.Status;
import bookers.bookkeeper.generics.BaseService;
import bookers.bookkeeper.user.User;
import bookers.bookkeeper.user.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class BookListService extends BaseService<BookEntry, BookListRepository> {

    private final UserService userService;
    private final BookService bookService;

    public BookListService(BookListRepository rep, UserService userService, BookService bookService) {
        super(rep);
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
        BookEntry saved = rep.save(bookEntryFound);
        updateBookGlobalScore(bookEntryFound.getBook());
        return saved;
    }

    public BookEntry addBookEntry(AddEntryDTO addEntryDTO, String username) {
        User user = userService.getUserByNameWithCheck(username);
        Book bookToAdd = bookService.getBook(addEntryDTO.getBookId());
        BookEntry bookEntry = new BookEntry();
        if (checkIfBookOnUserlist(username, bookToAdd.getId())) {
            throw new IllegalStateException("Book is already on your list");
        }
        bookEntry.setUser(user);
        bookEntry.setDateAdded(addEntryDTO.getDateAdded());
        bookEntry.setDateFinished(addEntryDTO.getDateFinished());
        bookEntry.setNotes(addEntryDTO.getNotes());
        bookEntry.setStatus(addEntryDTO.getStatus());
        bookEntry.setPagesRead(addEntryDTO.getPagesRead());
        bookEntry.setUserScore(addEntryDTO.getUserScore());
        bookEntry.setBook(bookToAdd);

        BookEntry saved = rep.save(bookEntry);
        updateBookGlobalScore(bookToAdd);
        return saved;
    }


    public Long deleteEntry(Long id) {
        Book bookToUpdate = getEntityById(id).getBook();
        Long deletedId = super.deleteEntityById(id);
        updateBookGlobalScore(bookToUpdate);
        return deletedId;
    }


    private boolean checkIfBookOnUserlist(String username, Long bookId) {
        return rep.existsByUser_UsernameAndBook_Id(username, bookId);
    }

    public List<BookEntry> getUserListSorted(String username, String sort, Integer pages, Integer pageSize) {
        Pageable page = PageRequest.of(pages, pageSize, Sort.by(sort));
        return rep.findBookEntriesByUser_Username(username, page);
    }

    public List<BookEntry> getUserListByStatus(String username, Status status) {
        return rep.findBookEntriesByUser_UsernameAndStatus(username, status);

    }

    public void updateBookGlobalScore(Book book) {
        book.setGlobalScore(rep.averageBookScore(book.getId()));
        bookService.addEntity(book);
    }

}
