package submit.ast;

import java.util.List;

public class AndExpression implements Node, Expression{
    List<UnaryRelExpression> unaryRelExpressions;

    @Override
    public void toCminus(StringBuilder builder, String prefix) {

    }
}
