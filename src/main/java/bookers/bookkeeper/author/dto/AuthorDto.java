package bookers.bookkeeper.author.dto;

import bookers.bookkeeper.Converter;
import bookers.bookkeeper.author.Author;
import bookers.bookkeeper.book.dto.BookDto;
import bookers.bookkeeper.book.dto.BookDtoConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {
    String name;
    List<BookDto> books;
    Long favorites;

    @JsonIgnore
    public Converter<AuthorDto, Author> getConverter(){
        return new AuthorDtoConverter(new BookDtoConverter());
    }

}
