package bookers.bookkeeper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Converter<DTO, T> {
    DTO convertToDto(T t);

    T convertFromDto(DTO t);

    List<DTO> convertListToDto(List<T> t);

    List<T> convertListFromDto(List<DTO> t);
}
