package bookers.bookkeeper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Converter<DTO, T> {
    public abstract DTO toDto(T t);

    public abstract T fromDto(DTO t);

    public List<DTO> listToDto(List<T> t) {
        return mapList(t, this::toDto);
    }

    public List<T> listFromDto(List<DTO> t) {
        return mapList(t, this::fromDto);
    }

    private static <A, B> List<B> mapList(List<A> list, Function<A, B> func) {
        return list.stream().map(func).collect(Collectors.toList());
    }
}
