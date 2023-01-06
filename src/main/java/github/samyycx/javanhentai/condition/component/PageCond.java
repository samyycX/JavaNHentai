package github.samyycx.javanhentai.condition.component;

public class PageCond implements Cond {

    private int page;

    public PageCond(int page) {
        this.page = page;
    }

    @Override
    public String toParam() {
        return String.format("pages:%d", page);
    }

}
