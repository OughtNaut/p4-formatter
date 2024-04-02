package submit.ast;

import java.util.List;

public class SumExpression implements Node, Expression{
    List<TermExpression> termExpressions;
    List<Sumop> sumops;

    public SumExpression(List<TermExpression> termExpressions, List<Sumop> sumops) {
        this.sumops = sumops;
        this.termExpressions = termExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {

    }
}
