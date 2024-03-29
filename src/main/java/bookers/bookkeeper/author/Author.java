package bookers.bookkeeper.author;

import bookers.bookkeeper.book.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "author", schema = "public", catalog = "BookKeeper")
@Data
public class Author extends RepresentationModel<Author> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authors")
    @RestResource(path = "books", rel = "books")
    private List<Book> books;

    @Column(name = "favorites")
    private Integer favorites;

}
