package bookers.bookkeeper.user.dto;

import bookers.bookkeeper.booklist.dto.BookEntryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends RepresentationModel<UserDTO> {
    private String username;
    private String email;
    private List<BookEntryDTO> userlist = new ArrayList<>();

}
