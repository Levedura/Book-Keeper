package bookers.bookkeeper.userprofile.dto;

import bookers.bookkeeper.author.dto.AuthorDTO;
import bookers.bookkeeper.book.dto.BookDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserProfileDTO extends RepresentationModel<UserProfileDTO> {
    private String userName;
    private String profileImage;
    private Double meanScore;
    private Integer pagesRead;
    private Integer booksRead;
    private List<BookDTO> favoriteBooks;
    private List<AuthorDTO> favoriteAuthors;

}

