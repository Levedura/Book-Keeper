package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.author.dto.AuthorDto;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    String title;
    String cover;
    List<AuthorDto> authors;
    List<String> genres;
    String language;
    Integer pages;
    Float globalscore;
}
