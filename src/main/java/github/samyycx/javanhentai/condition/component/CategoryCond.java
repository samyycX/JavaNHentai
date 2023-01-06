package github.samyycx.javanhentai.condition.component;

public class CategoryCond implements Cond {

    private final String category;

    public CategoryCond(String category) {
        this.category = category;
    }

    @Override
    public String toParam() {
        return String.format("category:'%s'", category);
    }
}
