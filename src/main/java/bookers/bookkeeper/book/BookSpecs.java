package bookers.bookkeeper.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {
    public static Specification<Book> hasTitle(String words) {
        return (Specification<Book>) (root, query, builder) ->
                builder.like(root.get("title"), "%" + words + "%");
    }

    public static Specification<Book> authorHasName(String name) {
        return (Specification<Book>) (root, query, builder) ->
                builder.like(root.join("authors").get("name"), "%" + name + "%");
    }

}
