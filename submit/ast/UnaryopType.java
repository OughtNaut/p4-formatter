package submit.ast;

public enum UnaryopType {
    MULT("*"), DIV("/"), MOD("%");

    private final String value;

    private UnaryopType(String value) {
        this.value = value;
    }

    public static UnaryopType fromString(String s) {
        for (UnaryopType at : UnaryopType.values()) {
            if (at.value.equals(s)) {
                return at;
            }
        }
        throw new RuntimeException("Illegal string in UnaryOp.fromString(): " + s);
    }

    @Override
    public String toString() {
        return value;
    }


}
