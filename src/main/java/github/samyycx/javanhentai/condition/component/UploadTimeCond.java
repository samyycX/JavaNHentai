package github.samyycx.javanhentai.condition.component;

public class UploadTimeCond implements Cond {

    private final String time;

    public UploadTimeCond(String time) {
        this.time = time;
    }

    public UploadTimeCond(int num, NTimeUnit nTimeUnit) {
        this.time = num+nTimeUnit.getUnit();
    }

    @Override
    public String toParam() {
        return String.format("uploaded:%s",time);
    }


}
