package github.samyycx.javanhentai.condition.component;

public class UploadTimeRangeCond implements Cond {

    private final String time;

    public UploadTimeRangeCond(String time) {
        this.time = time;
    }

    public UploadTimeRangeCond(NSymbol symbol, int num, NTimeUnit unit) {
        this.time = symbol.getSymbol()+num+unit.getUnit();
    }


    @Override
    public String toParam() {
        return String.format("uploaded:%s",time);
    }
}
