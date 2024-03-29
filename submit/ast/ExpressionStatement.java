package submit.ast;

import parser.CminusParser;

public class ExpressionStatement implements Statement, Node {
    Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }



    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append("\n").append(prefix);

        if (expression != null) {
            expression.toCminus(builder, "");
        }
        builder.append(";");

    }
}


