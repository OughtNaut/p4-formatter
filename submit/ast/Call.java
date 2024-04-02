package submit.ast;

import java.util.List;

public class Call implements Node, Expression{
    String id;
    List<Expression> expressions;

    public Call(String id, List<Expression> expressions) {
        this.id = id;
        this.expressions = expressions;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);
        builder.append(id).append("(");
        for (int i = 0; i < expressions.size()-1; i++) {
            expressions.get(i).toCminus(builder,prefix);
            builder.append(',');
        }
        if (expressions.size()!=0) {
            expressions.get(expressions.size()-1).toCminus(builder,prefix);
        }
        builder.append(")");
    }
}
