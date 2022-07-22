package bookers.bookkeeper.author;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {
    String name;
    List<String> books;

}
