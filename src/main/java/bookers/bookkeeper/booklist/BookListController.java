package bookers.bookkeeper.booklist;

import bookers.bookkeeper.booklist.dto.AddEntryDTO;
import bookers.bookkeeper.booklist.dto.BookEntryDTO;
import bookers.bookkeeper.booklist.dto.BookEntryDTOConverter;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userlist")
public class BookListController extends BaseController<BookEntry, BookEntryDTO, BookEntryDTOConverter, BookListService> {

    public BookListController(BookListService service, BookEntryDTOConverter converter) {
        super(service, converter);
    }

    @GetMapping("/{username}")
    public List<BookEntryDTO> getUserListByName(@PathVariable(name = "username") String username) {
        return converter.listToDto(service.getUserList(username));
    }

    @GetMapping("/{username}/{sort}/{pages}/{pageSize}")
    public List<BookEntryDTO> getSimpleSortPaging(@PathVariable String username, @PathVariable String sort, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return converter.listToDto(service.getUserListSorted(username, sort, pages, pageSize));
    }

    @PostMapping("/{username}")
    @PreAuthorize("authentication.name == #username")
    public BookEntryDTO addBookEntry(@PathVariable(name = "username") String username, @RequestBody AddEntryDTO bookEntryDto) {
        return converter.toDto(service.addBookEntry(bookEntryDto, username));
    }

    /*

        @PatchMapping("/{username}/{id}")
        @PreAuthorize("authentication.name == #username")
        public BookEntryDTO updateBookEntry(@PathVariable Long id, @RequestBody AddEntryDTO bookEntryDto, @PathVariable String username) {
            return converter.toDto(service.updateBookEntry(id, bookEntryDto));
        }
    */
    @PatchMapping("/{username}/{id}")
    @PreAuthorize("authentication.name == #username")
    public BookEntryDTO updateBookEntry(@PathVariable Long id, @RequestBody Map<String, Object> fieldMap, @PathVariable String username) {
        return converter.toDto(service.updateBookEntry(id, fieldMap));
    }

    @DeleteMapping("/{username}/{id}")
    @PreAuthorize("authentication.name == #username")
    public Long deleteBookEntry(@PathVariable Long id, @PathVariable String username) {
        return service.deleteEntry(id);
    }

}

