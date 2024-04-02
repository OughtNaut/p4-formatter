package submit.ast;

import java.util.List;

public class UnaryExpression implements Node, Expression{
    List<String> unaryops;
    Factor factor;

    public UnaryExpression(List<String> unaryops, Factor factor) {
        this.factor = factor;
        this.unaryops = unaryops;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);
        for (String u : unaryops) {
            builder.append(u);
        }
        factor.toCminus(builder,prefix);

    }
}
