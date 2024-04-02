package submit.ast;

import java.util.List;

public class UnaryExpression implements Node, Expression{
    List<Unaryop> unaryops;
    Factor factor;

    public UnaryExpression(List<Unaryop> unaryops, Factor factor) {
        this.factor = factor;
        this.unaryops = unaryops;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {


    }
}
