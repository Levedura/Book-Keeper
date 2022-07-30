package bookers.bookkeeper.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    Plan("Plan to read"),
    Reading("Reading..."),
    Finished("Finished reading");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
