package bookers.bookkeeper;

public enum Status {
    Plan("Plan to read"),
    Reading("Reading..."),
    Finished("Finished reading");

    private final String text;

    Status(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
