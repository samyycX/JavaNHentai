package github.samyycx.javanhentai.condition.component;

public class ParodyCond implements Cond {

    private final String parody;

    public ParodyCond(String parody) {
        this.parody = parody;
    }

    @Override
    public String toParam() {
        return String.format("parody:'%s'",parody);
    }
}
