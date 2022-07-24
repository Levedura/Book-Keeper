package bookers.bookkeeper.booklist;

import bookers.bookkeeper.bookentry.BookEntry;
import bookers.bookkeeper.bookentry.BookEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/userlist/{username}")
    public List<BookEntry> getUserListById(@PathVariable(name = "username") String username) {
        return bookListService.getUserList(username);
    }


    @GetMapping("/userlist/&{username}&{pages}&{pageSize}")
    public String getUserListSortedByUserScore(@PathVariable String username, @PathVariable String pages, @PathVariable String pageSize){
        return ": |";
        //return bookListService.getListSortedByUserScore(username,pages,pageSize);
    }

    @PostMapping("/userlist/{username}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public BookEntry addBookEntry(@PathVariable(name = "username") String username, @RequestBody BookEntryDTO bookEntryDto) {
        return bookListService.addBookEntry(bookEntryDto, username);
    }

    @PutMapping("/userlist/{id}&{username}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public BookEntry updateBookEntry(@PathVariable Long id, @RequestBody BookEntryDTO bookEntryDto, @PathVariable String username) {
        return bookListService.updateBookEntry(id, bookEntryDto, username);
    }

    @DeleteMapping("/userlist/{id}&{username}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public Long deleteBookEntry(@PathVariable Long id, @PathVariable String username) {
        return bookListService.deleteEntry(id);
    }

}

