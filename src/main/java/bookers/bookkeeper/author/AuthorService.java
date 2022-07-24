package bookers.bookkeeper.author;

import bookers.bookkeeper.BaseService;
import bookers.bookkeeper.author.dto.AuthorDto;
import bookers.bookkeeper.author.dto.AuthorDtoConverter;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static bookers.bookkeeper.author.dto.AuthorDtoConverter.*;

@Service
public class AuthorService extends BaseService<Author, AuthorRepository> {


    AuthorDtoConverter authorDtoConverter;

    @Autowired
    public AuthorService(AuthorRepository rep,AuthorDtoConverter authorDtoConverter) {
        super(rep);
        this.authorDtoConverter = authorDtoConverter;
    }


    public List<AuthorDto> getAllAuthors() {
        return authorDtoConverter.convertListToDto(rep.findAll());
    }
    public AuthorDto getAuthor(Long id){
        return authorDtoConverter.convertToDto(findEntityById(id));
    }

    public List<AuthorDto> getAuthorsOrderedByName(int pages, int pageSize) {
        return authorDtoConverter.convertListToDto(getEntitiesOrderedBy(rep::findByOrderByNameAsc, pages, pageSize));
    }

    public List<AuthorDto> getAuthorsOrderedByFavorites(Integer pages, Integer pageSize) {
        return authorDtoConverter.convertListToDto(getEntitiesOrderedBy(rep::findByOrderByFavorites, pages, pageSize));
    }

    public AuthorDto addAuthor(AuthorDto authorDto ){
        rep.save(authorDtoConverter.convertFromDto(authorDto));
        return authorDto;
    }
    public List<AuthorDto> addAuthors(List<AuthorDto> authorDto){
        rep.saveAll(authorDtoConverter.convertListFromDto(authorDto));
        return authorDto;
    }

    public Long deleteAuthor(Long id){
        return deleteEntityById(id);
    }

}
