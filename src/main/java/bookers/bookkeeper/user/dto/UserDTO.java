package bookers.bookkeeper.user.dto;

import bookers.bookkeeper.booklist.dto.BookEntryDTO;
import bookers.bookkeeper.generics.DTOId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends RepresentationModel<UserDTO> implements DTOId {
    private String username;
    private String email;
    private List<BookEntryDTO> userlist;

    @Override
    public Long getId() {
        return 0L;
    }
}
