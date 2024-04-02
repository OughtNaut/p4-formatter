package submit.ast;

import java.util.List;

public class TermExpression implements Node, Expression {
    List<UnaryExpression> unaryExpressions;
    List<Mulop> mulops;

    public TermExpression(List<UnaryExpression> unaryExpressions, List<Mulop> mulops) {
        this.mulops = mulops;
        this.unaryExpressions = unaryExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {

    }
//    unaryExpression
}
