package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.author.dto.AuthorSimpleView;
import bookers.bookkeeper.enums.Genres;

import java.util.List;

public interface BookSimpleView {
    String getTitle();
    List<AuthorSimpleView> getAuthors();
    List<Genres> getGenres();
    String getLanguage();
    Integer getPages();
    Double getGlobalScore();
}
