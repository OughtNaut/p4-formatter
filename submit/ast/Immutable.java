package submit.ast;

public class Immutable implements Expression, Node{
    private final Expression expression;
    private final Call call;
    private final String constant;

    public Immutable(Expression expression, Call call, String constant) {
        this.call = call;
        this.expression = expression;
        this.constant = constant;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {

    }
}
