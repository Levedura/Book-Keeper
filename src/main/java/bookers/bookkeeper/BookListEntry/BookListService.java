package bookers.bookkeeper.BookListEntry;

import bookers.bookkeeper.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class BookListService extends BaseService<BookEntry, BookListRepository> {

    @Autowired
    public BookListService(BookListRepository userListRepository) {
        super(userListRepository);
    }

    @GetMapping("/userlist/{id}")
    public BookEntry getUserListById(@PathVariable(name = "id") Long userId){
        return findById(userId);
    }

}
