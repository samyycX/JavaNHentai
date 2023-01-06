package github.samyycx.javanhentai.condition.component;

public class LanguageCond implements Cond {

    private final String language;

    public LanguageCond(String language) {
        this.language = language;
    }

    @Override
    public String toParam() {
        return String.format("language:'%s'",language);
    }
}
