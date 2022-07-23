package bookers.bookkeeper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DtoConverter<DTO,T> implements Converter<DTO,T> {

    @Override
    public List<DTO> convertListToDto(List<T> t) {
        return t.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<T> convertListFromDto(List<DTO> t) {
        return t.stream().map(this::convertFromDto).collect(Collectors.toList());
    }
}
