package submit.ast;

import parser.CminusParser;

public class WhileStatement implements Node, Statement {
    OrExpression orExpression;
    Statement statement;

    public WhileStatement(OrExpression orExpression, Statement statement) {
        this.orExpression = orExpression;
        this.statement = statement;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append("\n");
        builder.append(prefix);
        builder.append("while (");
        orExpression.toCminus(builder, prefix);
        builder.append(") ");
        prefix += " ";
        statement.toCminus(builder,prefix);
    }
}
