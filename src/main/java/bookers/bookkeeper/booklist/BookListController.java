package bookers.bookkeeper.booklist;

import bookers.bookkeeper.booklist.dto.AddEntryDTO;
import bookers.bookkeeper.booklist.dto.BookEntryDTO;
import bookers.bookkeeper.enums.Status;
import bookers.bookkeeper.generics.BaseController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/userlist")
public class BookListController extends BaseController<BookEntry, BookEntryDTO, BookListService, BookListModelAssembler> {

    public BookListController(BookListService service, BookListModelAssembler modelAssembler) {
        super(service, modelAssembler);
    }

    @GetMapping("/user/{username}")
    public CollectionModel<EntityModel<BookEntryDTO>> getUserList(@PathVariable(name = "username") String username) {
        return modelAssembler.toCollectionModel((service.getUserList(username)));
    }

    @GetMapping("/{username}/{status}")
    public CollectionModel<EntityModel<BookEntryDTO>> getUserListByStatus(@PathVariable(name = "username") String username, @PathVariable String status) {
        return modelAssembler.toCollectionModel(service.getUserListByStatus(username, Status.valueOf(status)));
    }

    @GetMapping("/{username}/{sort}/{pages}/{pageSize}")
    public CollectionModel<EntityModel<BookEntryDTO>> getSimpleSortPaging(@PathVariable String username, @PathVariable String sort, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return modelAssembler.toCollectionModel(service.getUserListSorted(username, sort, pages, pageSize));
    }

    @PostMapping("/{username}")
    @PreAuthorize("authentication.name == #username")
    public EntityModel<BookEntryDTO> addBookEntry(@PathVariable(name = "username") String username, @RequestBody AddEntryDTO bookEntryDto) {
        return modelAssembler.toModel(service.addBookEntry(bookEntryDto, username));
    }

    @PatchMapping("/{username}/{id}")
    @PreAuthorize("authentication.name == #username")
    public EntityModel<BookEntryDTO> updateBookEntry(@PathVariable Long id, @RequestBody Map<String, Object> fieldMap, @PathVariable String username) {
        return modelAssembler.toModel(service.updateBookEntry(id, fieldMap));
    }

    @DeleteMapping("/{username}/{id}")
    @PreAuthorize("authentication.name == #username")
    public Long deleteBookEntry(@PathVariable Long id, @PathVariable String username) {
        return service.deleteEntry(id);
    }

}

