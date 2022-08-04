package bookers.bookkeeper.author.dto;

import bookers.bookkeeper.generics.DTOId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthorDTO extends RepresentationModel<AuthorDTO> implements DTOId {
    private Long id;
    private String name;
    private List<Long> books;
    private Integer favorites;
}
