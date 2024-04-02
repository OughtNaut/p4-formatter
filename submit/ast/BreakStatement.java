package submit.ast;

public class BreakStatement implements Node, Statement{
    public BreakStatement() {

    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {

        builder.append("\n").append(prefix).append("break;");
    }
}
