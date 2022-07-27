package bookers.bookkeeper.booklist;

import bookers.bookkeeper.booklist.dto.BookEntryDTO;
import bookers.bookkeeper.booklist.dto.BookEntryDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookListController {

    BookListService bookListService;
    BookEntryDTOConverter bookEntryDTOConverter;

    @Autowired
    public BookListController(BookListService bookListService, BookEntryDTOConverter bookDTOConverter) {
        this.bookListService = bookListService;
        this.bookEntryDTOConverter = bookDTOConverter;
    }

    @GetMapping("/userlist/{username}")
    public List<BookEntryDTO> getUserListById(@PathVariable(name = "username") String username) {
        return bookEntryDTOConverter.listToDto(bookListService.getUserList(username));
    }

    @GetMapping("/userlist/&{username}&{pages}&{pageSize}")
    public List<BookEntryDTO> getUserListSortedByUserScore(@PathVariable String username, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return bookEntryDTOConverter.listToDto(bookListService.getListSortedByUserScore(username,pages,pageSize));
    }

    @PostMapping("/userlist/{username}/{bookID}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public BookEntryDTO addBookEntry(@PathVariable(name = "username") String username, @RequestBody BookEntryDTO bookEntryDto, @PathVariable Long bookID) {
        return bookEntryDTOConverter.toDto(bookListService.addBookEntry(bookEntryDTOConverter.fromDto(bookEntryDto), username,bookID));
    }

    @PutMapping("/userlist/{id}&{username}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public BookEntryDTO updateBookEntry(@PathVariable Long id, @RequestBody BookEntryDTO bookEntryDto, @PathVariable String username) {
        return bookEntryDTOConverter.toDto(bookListService.updateBookEntry(id, bookEntryDTOConverter.fromDto(bookEntryDto), username));
    }

    @DeleteMapping("/userlist/{id}&{username}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public Long deleteBookEntry(@PathVariable Long id, @PathVariable String username) {
        return bookListService.deleteEntry(id);
    }

}

