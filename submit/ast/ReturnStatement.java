package submit.ast;

public class ReturnStatement implements Node, Statement{
    Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression=expression;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append("\n").append(prefix);
        if (expression == null) {
            builder.append("\n").append(prefix).append("break;");
        } else {
            builder.append("\n").append(prefix).append("return ");
            expression.toCminus(builder,prefix);
            builder.append(";");
        }
    }
}
