package submit.ast;

public class Factor implements Node, Expression{
    Immutable immutable;
    Mutable mutable;

    public Factor(Immutable immutable, Mutable mutable) {
        this.immutable = immutable;
        this.mutable = mutable;
    }


    @Override
    public void toCminus(StringBuilder builder, String prefix) {
        if (immutable != null) {
            immutable.toCminus(builder,prefix);
        } else {
            mutable.toCminus(builder,prefix);
        }
    }
}
