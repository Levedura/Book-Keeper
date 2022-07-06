package bookers.bookkeeper.book;

import bookers.bookkeeper.Genres;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book", schema = "public", catalog = "BookRep")
@Data
public class Book {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "cover")
    private String cover;

    @ElementCollection(targetClass = Genres.class)
    @CollectionTable(name = "bookgenres", joinColumns = @JoinColumn(name = "bookid"))
    @Enumerated(EnumType.STRING)
    private List<Genres> genres;
    @Column(name = "language")
    private String language;

    @Column(name = "pages")
    private Integer pages;

}
