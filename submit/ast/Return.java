/*
 * Code formatter project
 * CS 4481
 */
package submit.ast;

/**
 *
 * @author edwajohn
 */
public class Return implements Statement {

  private final Expression expr;

  public Return(Expression expr) {
    this.expr = expr;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    builder.append("\n").append(prefix);

    if (expr == null) {
      builder.append("return;");
    } else {
      builder.append("return ");
      expr.toCminus(builder, prefix);
      builder.append(";");
    }
  }

}
