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

        builder.append("\n").append(prefix).append("{");
        prefix += "  ";

        for (VarDeclaration declaration : declarations) {
            declaration.toCminus(builder, prefix);
        }
        for (Statement statement : statements) {
            if (statement == null) break;
            statement.toCminus(builder, prefix);
        }
        prefix = prefix.substring(2);
        builder.append("\n").append(prefix).append("}");
    }
}
