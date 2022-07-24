package bookers.bookkeeper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperBean {
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
