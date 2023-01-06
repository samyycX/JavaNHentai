package github.samyycx.javanhentai.condition;

import github.samyycx.javanhentai.condition.component.Cond;

public class ConditionBuilder {

    private StringBuilder builder = new StringBuilder();

    public ConditionBuilder include(Cond cond) {
        builder.append(cond.toParam());
        builder.append(" ");
        return this;
    }

    public ConditionBuilder exclude(Cond cond) {
        builder.append("-");
        builder.append(cond.toParam());
        builder.append(" ");
        return this;
    }

    public String build() {
        return builder.toString();
    }

}
