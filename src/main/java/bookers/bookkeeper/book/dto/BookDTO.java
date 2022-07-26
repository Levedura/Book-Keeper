package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.author.dto.AuthorDTO;
import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    String title;
    String cover;
    List<AuthorDTO> authors;
    List<String> genres;
    String language;
    Integer pages;
    Float globalscore;
}
