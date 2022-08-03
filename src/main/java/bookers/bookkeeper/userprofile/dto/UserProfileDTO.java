package bookers.bookkeeper.userprofile.dto;

import bookers.bookkeeper.author.dto.AuthorDTO;
import bookers.bookkeeper.book.dto.BookDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserProfileDTO {
    private String userName;
    private Double meanScore;
    private Integer pagesRead;
    private Integer booksRead;
    private List<BookDTO> favoriteBooks;
    private List<AuthorDTO> favoriteAuthors;
}

