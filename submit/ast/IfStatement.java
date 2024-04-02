package submit.ast;

import java.util.List;

public class IfStatement implements Statement, Node {
//    may be forced to implement simpleexpression
    OrExpression orExpression;
    List<Statement>statements;

    public IfStatement(OrExpression orExpression, List<Statement> statements) {
        this.orExpression = orExpression;
        this.statements = statements;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append("\n").append(prefix);
        builder.append("if (");
        orExpression.toCminus(builder,prefix);
        builder.append(")");
        prefix+=" ";
        statements.get(0).toCminus(builder, prefix);
        prefix = prefix.substring(1);
        if (statements.size()>1) {
            builder.append("\n").append(prefix);
            builder.append("else ");
            prefix += " ";
            statements.get(1).toCminus(builder, prefix);
        }

    }
}

