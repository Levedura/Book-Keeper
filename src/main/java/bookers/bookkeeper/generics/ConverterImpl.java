package bookers.bookkeeper.generics;

import bookers.bookkeeper.generics.Converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ConverterImpl<T, DTO> implements Converter<T,DTO> {
    public abstract DTO toDto(T t);

    public abstract T fromDto(DTO t);

    public List<DTO> listToDto(List<T> t) {
        return mapList(t, this::toDto);
    }

    public List<T> listFromDto(List<DTO> t) {
        return mapList(t, this::fromDto);
    }

    private <A, B> List<B> mapList(List<A> list, Function<A, B> func) {
        return list.stream().map(func).collect(Collectors.toList());
    }
}
