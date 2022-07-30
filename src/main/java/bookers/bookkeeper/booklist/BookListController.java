package bookers.bookkeeper.booklist;

import bookers.bookkeeper.booklist.dto.BookEntryDTO;
import bookers.bookkeeper.booklist.dto.BookEntryDTOConverter;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userlist")
public class BookListController extends BaseController<BookEntry, BookEntryDTO, BookEntryDTOConverter, BookListService> {

    public BookListController(BookListService service, BookEntryDTOConverter converter) {
        super(service, converter);
    }

    @GetMapping("/u={username}")
    public List<BookEntryDTO> getUserListByName(@PathVariable(name = "username") String username) {
        return converter.listToDto(service.getUserList(username));
    }

    @GetMapping("/{username}/{sort}/{pages}/{pageSize}")
    public List<BookEntryDTO> getSimpleSortPaging(@PathVariable String username, @PathVariable String sort, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return converter.listToDto(service.getUserListSorted(username,sort,pages,pageSize));
    }

    @PostMapping("/{username}/{bookID}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public BookEntryDTO addBookEntry(@PathVariable(name = "username") String username, @RequestBody BookEntryDTO bookEntryDto, @PathVariable Long bookID) {
        return converter.toDto(service.addBookEntry(converter.fromDto(bookEntryDto), username, bookID));
    }

    @PutMapping("/{username}/{id}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public BookEntryDTO updateBookEntry(@PathVariable Long id, @RequestBody BookEntryDTO bookEntryDto, @PathVariable String username) {
        return converter.toDto(service.updateBookEntry(id, converter.fromDto(bookEntryDto), username));
    }

    @DeleteMapping("/{username}/{id}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public Long deleteBookEntry(@PathVariable Long id, @PathVariable String username) {
        return service.deleteEntry(id);
    }

}

