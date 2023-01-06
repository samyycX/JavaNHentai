package github.samyycx.javanhentai.condition.component;

public enum NSymbol {

    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_EQUAL_THAN(">="),
    LESS_EQUAL_THAN("<=")

    ;
    String s;

    NSymbol(String s) {
        this.s = s;
    }

    public String getSymbol() {
        return s;
    }
}