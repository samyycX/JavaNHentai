package github.samyycx.javanhentai.condition.component;

public class CharacterCond implements Cond {

    private final String character;

    public CharacterCond(String character) {
        this.character = character;
    }

    @Override
    public String toParam() {
        return String.format("character:'%s'",character);
    }
}
