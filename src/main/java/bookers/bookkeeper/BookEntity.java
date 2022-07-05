package bookers.bookkeeper;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "public", catalog = "BookRep")
public class BookEntity {
    private int id;
    private String title;
    private String cover;
    private List<Genres> genres;
    private String language;
    private Integer pages;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @ElementCollection(targetClass = Genres.class)
    @JoinTable(name = "bookgenres", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "genres", nullable = false)
    @Enumerated(EnumType.STRING)
    public List<Genres> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "pages")
    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(cover, that.cover) &&
                Objects.equals(genres, that.genres) &&
                Objects.equals(language, that.language) &&
                Objects.equals(pages, that.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, cover, genres, language, pages);
    }
}
