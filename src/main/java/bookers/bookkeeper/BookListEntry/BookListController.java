package bookers.bookkeeper.BookListEntry;

import org.springframework.beans.factory.annotation.Autowired;
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
    @PreAuthorize(value = "authentication.principal.equals(#name)")
    public BookEntry addBookEntry(@PathVariable(name = "name") String name, @RequestBody BookEntryDTO bookEntryDto) {
        return bookListService.addBookEntry(bookEntryDto, name);
    }

}

