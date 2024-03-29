package bookers.bookkeeper.book;

import bookers.bookkeeper.author.Author;
import bookers.bookkeeper.enums.Genres;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book", schema = "public", catalog = "BookKeeper")
@Data
public class Book extends RepresentationModel<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "cover")
    private String cover;

    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @RestResource(path = "authors", rel = "authors")
    private List<Author> authors;

    @ElementCollection(targetClass = Genres.class)
    @CollectionTable(name = "bookgenres", joinColumns = @JoinColumn(name = "book_id"))
    @Enumerated(EnumType.STRING)
    private List<Genres> genres;

    @Column(name = "language")
    private String language;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "score")
    private Double globalScore;

    private String synopsis;


}
