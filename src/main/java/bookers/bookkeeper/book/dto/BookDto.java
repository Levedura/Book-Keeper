package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.author.dto.AuthorDto;
import bookers.bookkeeper.enums.Genres;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    String title;
    String cover;
    List<AuthorDto> authors;
    List<Genres> genres;
    String language;
    Integer pages;
    Float globalscore;
}
