package bookers.bookkeeper.author.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@EqualsAndHashCode(callSuper = true)
@Data
@Relation(collectionRelation = "authors", itemRelation = "author")
public class AuthorDTO extends RepresentationModel<AuthorDTO> {
    private Long id;
    private String name;
    //private List<Long> books;
    private Integer favorites;
}
