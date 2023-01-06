package github.samyycx.javanhentai.condition.component;

public enum NTimeUnit {

    HOUR("h"),
    DAY("d"),
    WEEK("w"),
    MONTH("m"),
    YEAR("y")
    ;

    private String unit;

    NTimeUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}