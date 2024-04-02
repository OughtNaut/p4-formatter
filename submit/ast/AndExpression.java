package submit.ast;

import java.util.List;

public class AndExpression implements Node, Expression{
    List<UnaryRelExpression> unaryRelExpressions;

    public AndExpression(List<UnaryRelExpression> unaryRelExpressions) {
        this.unaryRelExpressions = unaryRelExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);
//        System.out.println(this.unaryRelExpressions.size() + " size inside constructor");
        builder.append(prefix);
//Why isnt this working check later
        for (int i = 0; i < unaryRelExpressions.size()-1; i++) {
            unaryRelExpressions.get(i).toCminus(builder,prefix);
            builder.append("&&");
        }
        unaryRelExpressions.get(unaryRelExpressions.size()-1).toCminus(builder,prefix);

    }
}
