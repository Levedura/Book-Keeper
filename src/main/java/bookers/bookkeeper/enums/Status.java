package bookers.bookkeeper.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    Plan("Plan to read"),
    Reading("Reading..."),
    Finished("Finished reading");

    private final String text;

    Status(String text) {
        this.text = text;
    }

    @Override
    @JsonValue
    public String toString() {
        return text;
    }
}
