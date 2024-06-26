package submit.ast;

public enum FunType {

    INT("int"), BOOL("bool"), CHAR("char"), VOID("void");

    private final String value;

    private FunType(String value) {
        this.value = value;
    }

    public static FunType fromString(String s) {
        for (FunType vt : FunType.values()) {
            if (vt.value.equals(s)) {
                return vt;
            }
        }
        throw new RuntimeException("Illegal string in VarType.fromString()");
    }

    @Override
    public String toString() {
        return value;
    }

}