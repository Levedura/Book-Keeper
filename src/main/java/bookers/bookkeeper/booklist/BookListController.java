package bookers.bookkeeper.booklist;

import bookers.bookkeeper.booklist.dto.AddEntryDTO;
import bookers.bookkeeper.booklist.dto.BookEntryDTO;
import bookers.bookkeeper.enums.Status;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/userlist/{username}")
public class BookListController {
    BookListService listService;
    BookListModelAssembler listModelAssembler;

    public BookListController(BookListService listService, BookListModelAssembler listModelAssembler) {
        this.listService = listService;
        this.listModelAssembler = listModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<BookEntryDTO>> getUserList(@PathVariable(name = "username") String username) {
        return listModelAssembler.toCollectionModel((listService.getUserList(username)));
    }

    @GetMapping("/{status}")
    public CollectionModel<EntityModel<BookEntryDTO>> getUserListByStatus(@PathVariable(name = "username") String username, @PathVariable String status) {
        return listModelAssembler.toCollectionModel(listService.getUserListByStatus(username, Status.valueOf(status)));
    }

    @GetMapping("/{sort}/{pages}/{pageSize}")
    public CollectionModel<EntityModel<BookEntryDTO>> getSimpleSortPaging(@PathVariable String username, @PathVariable String sort, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return listModelAssembler.toCollectionModel(listService.getUserListSorted(username, sort, pages, pageSize));
    }

    @PostMapping
    @PreAuthorize("authentication.name == #username")
    public EntityModel<BookEntryDTO> addBookEntry(@PathVariable(name = "username") String username, @RequestBody AddEntryDTO bookEntryDto) {
        return listModelAssembler.toModel(listService.addBookEntry(bookEntryDto, username));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("authentication.name == #username")
    public EntityModel<BookEntryDTO> updateBookEntry(@PathVariable Long id, @RequestBody Map<String, Object> fieldMap, @PathVariable String username) {
        return listModelAssembler.toModel(listService.updateBookEntry(id, fieldMap));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("authentication.name == #username")
    public Long deleteBookEntry(@PathVariable Long id, @PathVariable String username) {
        return listService.deleteEntry(id);
    }

}

