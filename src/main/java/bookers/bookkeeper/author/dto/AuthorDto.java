package bookers.bookkeeper.author.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthorDto extends RepresentationModel<AuthorDto> {
    Long id;
    String name;
    List<Long> books;
    Long favorites;
}
