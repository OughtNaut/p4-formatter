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

    }
}
