package github.samyycx.javanhentai.condition.component;

public class TitleCond implements Cond {

    private final String title;

    public TitleCond(String title) {
        this.title = title;
    }

    @Override
    public String toParam() {
        return String.format("'%s'",title);
    }

}
