package github.samyycx.javanhentai.condition.component;

public class TagCond implements Cond {

    private final String tag;

    public TagCond(String tag) {
        this.tag = tag;
    }

    @Override
    public String toParam() {
        return String.format("tag:'%s'",tag);
    }
}
