package submit.ast;

import java.util.List;

public class FunDeclaration implements Declaration, Node {

    private final VarType type;
    private final String id;
    private final List<String> params;

    public FunDeclaration(VarType type, String id, List<String> params ) {

        this.type = type;
        this.id = id;
        this.params = params;
        for (String param : params) {
//            System.out.println(param);
        }
    }
    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        builder.append(prefix);
    }
}
