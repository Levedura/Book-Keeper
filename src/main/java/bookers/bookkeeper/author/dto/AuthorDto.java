package bookers.bookkeeper.author.dto;

import bookers.bookkeeper.book.dto.BookDto;
import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {
    String name;
    List<BookDto> books;
    Long favorites;
}
