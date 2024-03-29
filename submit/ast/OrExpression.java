package submit.ast;

import java.util.List;

public class OrExpression implements Expression, Node{
    List<AndExpression> andExpressions;

    public OrExpression(List<AndExpression> andExpressions) {
        this.andExpressions = andExpressions;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);
        for (AndExpression ex : andExpressions) {
            ex.toCminus(builder, prefix);
            builder.append("&&");
        }
    }
}
