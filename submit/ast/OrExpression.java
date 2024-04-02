package submit.ast;

import java.util.List;

public class OrExpression implements Expression, Node{
    List<AndExpression> andExpressions;

    public OrExpression(List<AndExpression> andExpressions) {
        this.andExpressions = andExpressions;

    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        System.out.println(this.andExpressions.size() + " size inside constructor");
        builder.append(prefix);
//Why isnt this working check later
        for (int i = 0; i < andExpressions.size()-1; i++) {
            andExpressions.get(i).toCminus(builder,prefix);
            builder.append("||");
        }
        andExpressions.get(andExpressions.size()-1).toCminus(builder,prefix);
    }
}
