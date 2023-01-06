package github.samyycx.javanhentai.response;

public enum NSortMethod {

    DEFAULT(""),

    RECENT(""),

    // 今日最热门
    POPULAR_TODAY("popular-today"),

    // 这周最热门
    POPULAR_WEEK("popular-week"),

    // 月度最热门
    POPULAR_MONTH("popular-month"),

    // 统计最热门
    POPULAR("popular")

    ;


    private String type;

    NSortMethod(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
