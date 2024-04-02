/*
 * Code formatter project
 * CS 4481
 */
package submit.ast;

import java.util.Objects;

/**
 *
 * @author edwajohn
 */
public class BinaryOperator implements Expression {

  private final Expression lhs, rhs;
  private final BinaryOperatorType type;

  public BinaryOperator(Expression lhs, BinaryOperatorType type, Expression rhs) {
    this.lhs = lhs;
    this.type = type;
    this.rhs = rhs;
  }

  public BinaryOperator(Expression lhs, String type, Expression rhs) {
    this.lhs = lhs;
    this.type = BinaryOperatorType.fromString(type);
    this.rhs = rhs;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    lhs.toCminus(builder, prefix);
    if (!(Objects.equals(type.toString(), "--") || Objects.equals(type.toString(),"++"))) {
      builder.append(" ");
    }
    builder.append(type);
    if (!(Objects.equals(type.toString(), "--") || Objects.equals(type.toString(),"++"))) {
      builder.append(" ");
    }
    if (rhs != null) {
      rhs.toCminus(builder, prefix);

    }
  }

}
