package bookers.bookkeeper.generics;

import java.util.List;

public interface Converter<T, DTO> {
    DTO toDto(T t);

    T fromDto(DTO t);

    List<DTO> listToDto(List<T> t);

    List<T> listFromDto(List<DTO> t);

}
