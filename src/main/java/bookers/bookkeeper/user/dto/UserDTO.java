package bookers.bookkeeper.user.dto;

import bookers.bookkeeper.booklist.dto.BookEntryDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String email;
    private List<BookEntryDTO> userlist;
}
