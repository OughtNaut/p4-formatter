package submit.ast;

import parser.CminusParser;

public class ExpressionStatement implements Statement, Node {
    Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }



    @Override
    public void toCminus(StringBuilder builder, String prefix) {

        if (expression != null) {
            builder.append("\n").append(prefix);
            expression.toCminus(builder, "");
            builder.append(";");
            return;
        }
        builder.append(";");

    }
}


