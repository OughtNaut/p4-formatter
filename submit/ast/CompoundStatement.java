package submit.ast;

import java.util.List;

public class CompoundStatement implements Statement, Node{
    private final List<Statement> statements;
    private final List<VarDeclaration> declarations;

    public CompoundStatement(List<Statement> statements, List<VarDeclaration> declarations ) {
        this.declarations = declarations;
        this.statements = statements;
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append("\n").append(prefix);
        builder.append("{").append("\n").append(" ");
        for (VarDeclaration declaration : declarations) {
            declaration.toCminus(builder, "");
        }
        for (Statement statement : statements) {
            if (statement == null) break;
            statement.toCminus(builder, "");
        }
        builder.append("\n}");
    }
}
