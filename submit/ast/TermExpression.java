package submit.ast;

import java.util.List;

public class TermExpression implements Node, Expression {
    List<UnaryExpression> unaryExpressions;
    List<String> mulops;

    public TermExpression(List<UnaryExpression> unaryExpressions, List<String> mulops) {
        this.mulops = mulops;
        this.unaryExpressions = unaryExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        for (int i = 0; i < mulops.size(); i++) {
            unaryExpressions.get(i).toCminus(builder,prefix);
            builder.append(mulops.get(i));
        }
        unaryExpressions.get(unaryExpressions.size()-1).toCminus(builder,prefix);
    }
//    unaryExpression
}
