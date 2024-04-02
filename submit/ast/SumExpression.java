package submit.ast;

import java.util.List;

public class SumExpression implements Node, Expression{
    List<TermExpression> termExpressions;
    List<String> sumops;

    public SumExpression(List<TermExpression> termExpressions, List<String> sumops) {
        this.sumops = sumops;
        this.termExpressions = termExpressions;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        if (sumops.size()>0) {
            for (int i = 0; i < sumops.size(); i++) {
                termExpressions.get(i).toCminus(builder,prefix);
                builder.append(sumops.get(i));
            }
        }
        termExpressions.get(termExpressions.size()-1).toCminus(builder,prefix);

    }
}
