package submit.ast;

import parser.CminusParser;

import java.util.List;

public class FunDeclaration implements Declaration, Node {

    private final VarType type;
    private final String id;
    private final List<CminusParser.ParamContext> params;
    private final Statement statement;

    public FunDeclaration(VarType type, String id, List<CminusParser.ParamContext> params, Statement statement ) {

        this.type = type;
        this.id = id;
        this.params = params;
        this.statement = statement;

    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append("\n").append(prefix);
        if (type == null) {
            builder.append("\n").append("void").append(" ");
        } else {
            builder.append("\n").append(type).append(" ");
        }
        builder.append(id).append('(');
        if (params.size() <= 1 ) {
            if (params.size()==0) {

            }
            else {
                builder.append(params.get(0).typeSpecifier().getText()).append(" ").append(params.get(0).paramId().getText());
            }

        } else {
            for (CminusParser.ParamContext param : params) {
                builder.append(param.typeSpecifier().getText()).append(" ").append(param.paramId().getText());
                builder.append(", ");
            }
            builder.delete(builder.length()-2,builder.length());
        }
        builder.append(")");
        statement.toCminus(builder, prefix);

//        builder.append("\n");
    }

}
