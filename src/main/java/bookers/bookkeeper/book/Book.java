package bookers.bookkeeper.book;

import bookers.bookkeeper.Genres;
import bookers.bookkeeper.author.Author;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book", schema = "public", catalog = "BookKeeper")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "cover")
    private String cover;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
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
    private float globalscore;

    public List<Author> fromAuthorIds(List<Long> authorIds) {
/*

        List<Author> authorList = new ArrayList<>();
        if()
        for (Long authorId : authorIds) {
            Author author = new Author();

            authorList.add();


        }
        authorIds.forEach();
*/
        return null;

    }
}
