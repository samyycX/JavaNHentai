package github.samyycx.javanhentai.condition.component;

public class PageRangeCond implements Cond {

    private final String s;

    public PageRangeCond(String s) {
        this.s = s;
    }

    // symbol:
    // > or < or >= or <=
    public PageRangeCond(String symbol, int num) {
        s = symbol+num;
    }

    public PageRangeCond(NSymbol symbol, int num) {
        s = symbol.getSymbol()+num;
    }

    @Override
    public String toParam() {
        return String.format("pages:%s",s);
    }



}
