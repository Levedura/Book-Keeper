package bookers.bookkeeper;

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
    Historical_Fiction("Historical_Fiction"),
    Science_Fiction("Science_Fiction"),
    Childrens("Childrens"),
    Memoir("Memoir"),
    Cooking("Cooking"),
    Art("Art"),
    Self_Help("Self_Help"),
    Development("Development"),
    Motivational("Motivational"),
    Health("Health"),
    History("History"),
    Travel("Travel"),
    Guide("Guide"),
    Families_Relationships("Families_Relationships"),
    Humor("Humor");

    private final String name;

    Genres(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
