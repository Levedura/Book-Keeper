package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.author.dto.AuthorDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Relation(collectionRelation = "books", itemRelation = "book")
public class BookDTO extends RepresentationModel<BookDTO> {
    private Long id;
    private String title;
    private String cover;
    private List<AuthorDTO> authors;
    private List<String> genres;
    private String language;
    private Integer pages;
    private Double globalScore;
    private String synopsis;
}
