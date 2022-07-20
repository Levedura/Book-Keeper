package bookers.bookkeeper.booklist;

import bookers.bookkeeper.bookentry.BookEntry;
import bookers.bookkeeper.bookentry.BookEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookListController {

    BookListService bookListService;

    @Autowired
    public BookListController(BookListService bookListService) {
        this.bookListService = bookListService;
    }

    @GetMapping("/userlist/{name}")
    public List<BookEntry> getUserListById(@PathVariable(name = "name") String username) {
        return bookListService.getUserList(username);
    }

    @PostMapping("/userlist/{name}")
    @PreAuthorize(value = "authentication.principal.username == #name")
    public BookEntry addBookEntry(@PathVariable(name = "name") String name, @RequestBody BookEntryDTO bookEntryDto) {
        return bookListService.addBookEntry(bookEntryDto, name);
    }
    @PutMapping("/userlist/{id}&{username}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public BookEntry updateBookEntry(@PathVariable Long id, @RequestBody BookEntryDTO bookEntryDto, @PathVariable String username){
        return bookListService.updateBookEntry(id,bookEntryDto,username);
    }

    @DeleteMapping("/userlist/{id}&{username}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public ResponseEntity<Long> deleteBookEntry(@PathVariable Long id, @PathVariable String username){
        return bookListService.deleteEntry(id);
    }

}

