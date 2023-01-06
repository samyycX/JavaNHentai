package github.samyycx.javanhentai.condition.component;

public class GroupCond implements Cond {

    private final String group;

    public GroupCond(String group) {
        this.group = group;
    }

    @Override
    public String toParam() {
        return String.format("group:'%s'",group);
    }

}
