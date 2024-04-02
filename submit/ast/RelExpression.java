package submit.ast;

import java.util.List;

public class RelExpression implements Node, Expression{
    List<SumExpression> sumExpressions;
    List<String> relops;
    public RelExpression(List<SumExpression> sumExpressions, List<String> relops) {
        this.sumExpressions = sumExpressions;
        this.relops = relops;
    }


    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);
        for (int i = 0; i < sumExpressions.size()-1; i++) {
            sumExpressions.get(i).toCminus(builder,prefix);
            builder.append(relops.get(i));
        }
        sumExpressions.get(sumExpressions.size()-1).toCminus(builder,prefix);
    }
}
