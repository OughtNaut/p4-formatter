package submit.ast;

import org.antlr.v4.runtime.tree.TerminalNode;
import parser.CminusParser;

import java.util.List;

public class UnaryRelExpression implements Node, Expression{
    List<TerminalNode> bangs;
    RelExpression relExpression;

    public UnaryRelExpression(List<TerminalNode> bangs, RelExpression relExpression) {
        this.bangs = bangs;
        this.relExpression = relExpression;
    }

    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);
        for (TerminalNode bang : bangs) {
            builder.append(bang.getText());
        }
        relExpression.toCminus(builder, prefix);
    }
}
