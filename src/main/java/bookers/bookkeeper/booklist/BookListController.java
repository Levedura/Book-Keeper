package bookers.bookkeeper.booklist;

import bookers.bookkeeper.booklist.dto.BookEntryDTO;
import bookers.bookkeeper.booklist.dto.BookEntryDTOConverter;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userlist")
public class BookListController extends BaseController<BookEntry,BookEntryDTO,BookEntryDTOConverter,BookListService> {

    public BookListController(BookListService service, BookEntryDTOConverter converter) {
        super(service, converter);
    }

    @GetMapping("/{username}")
    public List<BookEntryDTO> getUserListByName(@PathVariable(name = "username") String username) {
        return converter.listToDto(service.getUserList(username));
    }

    @GetMapping("/score/{username}&{pages}&{pageSize}")
    public List<BookEntryDTO> getUserListSortedByUserScore(@PathVariable String username, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return converter.listToDto(service.getListSortedByUserScore(username,pages,pageSize));
    }

    @GetMapping("/dateadded/{username}&{pages}&{pageSize}")
    public List<BookEntryDTO> getUserListSortedByDateAdded(@PathVariable String username, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return converter.listToDto(service.getListSortedByDateAdded(username,pages,pageSize));
    }

    @GetMapping("/datefinished/{username}&{pages}&{pageSize}")
    public List<BookEntryDTO> getUserListSortedByDateFinished(@PathVariable String username, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return converter.listToDto(service.getListSortedByDateFinished(username,pages,pageSize));
    }

    @GetMapping("/pagesread/{username}&{pages}&{pageSize}")
    public List<BookEntryDTO> getUserListSortedByPagesRead(@PathVariable String username, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return converter.listToDto(service.getListSortedByPagesRead(username,pages,pageSize));
    }

    @PostMapping("/{username}/{bookID}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public BookEntryDTO addBookEntry(@PathVariable(name = "username") String username, @RequestBody BookEntryDTO bookEntryDto, @PathVariable Long bookID) {
        return converter.toDto(service.addBookEntry(converter.fromDto(bookEntryDto), username,bookID));
    }

    @PutMapping("/{username}&{id}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public BookEntryDTO updateBookEntry(@PathVariable Long id, @RequestBody BookEntryDTO bookEntryDto, @PathVariable String username) {
        return converter.toDto(service.updateBookEntry(id, converter.fromDto(bookEntryDto), username));
    }

    @DeleteMapping("/{username}&{id}")
    @PreAuthorize(value = "authentication.principal.username == #username")
    public Long deleteBookEntry(@PathVariable Long id, @PathVariable String username) {
        return service.deleteEntry(id);
    }

}

