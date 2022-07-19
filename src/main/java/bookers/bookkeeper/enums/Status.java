package bookers.bookkeeper.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    Plan("Plan to read"),
    Reading("Reading..."),
    Finished("Finished reading");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.name;
    }

}
