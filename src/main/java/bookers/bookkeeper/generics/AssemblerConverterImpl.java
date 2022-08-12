package bookers.bookkeeper.generics;

public abstract class AssemblerConverterImpl<T, DTO, Con
        , C extends Converter<T, DTO>> implements AssemblerConverter<T, DTO> {

    protected final C converter;

    public AssemblerConverterImpl(C converter) {
        this.converter = converter;
    }

    public Converter<T, DTO> getConverter() {
        return converter;
    }
}
