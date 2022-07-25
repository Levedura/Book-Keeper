package bookers.bookkeeper.enums;

import com.fasterxml.jackson.annotation.JsonValue;


public enum Genres {

    Fantasy("Fantasy"),
    Adventure("Adventure"),
    Romance("Romance"),
    Contemporary("Romance"),
    Dystopian("Dystopian"),
    Mystery("Mystery"),
    Horror("Horror"),
    Thriller("Thriller"),
    Paranormal("Paranormal"),
    Historical_Fiction("Historical Fiction"),
    Science_Fiction("Science Fiction"),
    Childrens("Childrens"),
    Memoir("Memoir"),
    Cooking("Cooking"),
    Art("Art"),
    Self_Help("Self Help"),
    Development("Development"),
    Motivational("Motivational"),
    Health("Health"),
    History("History"),
    Travel("Travel"),
    Guide("Guide"),
    Families_Relationships("Families Relationships"),
    Humor("Humor");

    private final String name;

    Genres(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    @JsonValue
    public String toString() {
        return name;
    }
}
