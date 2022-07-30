package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.author.dto.AuthorDTO;
import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    private String title;
    private String cover;
    private List<AuthorDTO> authors;
    private List<String> genres;
    private String language;
    private Integer pages;
    private Float globalscore;
    private String synopsis;
}
