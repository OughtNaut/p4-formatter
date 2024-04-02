package submit;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import parser.CminusBaseVisitor;
import parser.CminusParser;
import submit.ast.*;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class ASTVisitor extends CminusBaseVisitor<Node> {
    private final Logger LOGGER;
    private SymbolTable symbolTable;

    public ASTVisitor(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }

    private VarType getVarType(CminusParser.TypeSpecifierContext ctx) {
        if (ctx == null) {
            return null;
        }
        final String t = ctx.getText();
        return (t.equals("int")) ? VarType.INT : (t.equals("bool")) ? VarType.BOOL : VarType.CHAR;
    }

    @Override public Node visitProgram(CminusParser.ProgramContext ctx) {
        symbolTable = new SymbolTable();
        List<Declaration> decls = new ArrayList<>();
        for (CminusParser.DeclarationContext d : ctx.declaration()) {
            decls.add((Declaration) visitDeclaration(d));
        }
        return new Program(decls);
    }

    @Override public Node visitVarDeclaration(CminusParser.VarDeclarationContext ctx) {

        VarType type = getVarType(ctx.typeSpecifier());
        List<String> ids = new ArrayList<>();
        List<Integer> arraySizes = new ArrayList<>();
        for (CminusParser.VarDeclIdContext v : ctx.varDeclId()) {
            String id = v.ID().getText();
            ids.add(id);
            symbolTable.addSymbol(id, new SymbolInfo(id, type, false));
            if (v.NUMCONST() != null) {
                arraySizes.add(Integer.parseInt(v.NUMCONST().getText()));
            } else {
                arraySizes.add(-1);
            }
        }
        final boolean isStatic = false;
        return new VarDeclaration(type, ids, arraySizes, isStatic);
    }
    @Override public Node visitFunDeclaration(CminusParser.FunDeclarationContext ctx) {
        String id = ctx.ID().getText();
        SymbolInfo symbolInfo = new SymbolInfo(id, getVarType(ctx.typeSpecifier()), true);
        symbolTable.addSymbol(id, symbolInfo );
        CminusParser.TypeSpecifierContext typeSpecifier = ctx.typeSpecifier();
        VarType type;
        if (typeSpecifier == null) {
            type = null;
        } else {
            type = getVarType(ctx.typeSpecifier());
        }


        List<CminusParser.ParamContext> paramList = new ArrayList<CminusParser.ParamContext>() {
        };
        if (ctx.param(0) != null) {
            for (CminusParser.ParamContext p : ctx.param()) {
                paramList.add(p);
                SymbolInfo pInfo = new SymbolInfo(p.paramId().ID().getText(), getVarType(p.typeSpecifier()), true);

                symbolTable.addSymbol(p.paramId().ID().getText(), pInfo);
            }
        }
        Node statement = visitStatement(ctx.statement());
        return new FunDeclaration(type,ctx.ID().getText(), paramList, (Statement) statement);
    }

    @Override public Node visitReturnStmt(CminusParser.ReturnStmtContext ctx) {
        if (ctx.expression() != null) {
            return new Return((Expression) visitExpression(ctx.expression()));
        }
        return new Return(null);
    }

    @Override public Node visitConstant(CminusParser.ConstantContext ctx) {
        final Node node;
        if (ctx.NUMCONST() != null) {
            node = new NumConstant(Integer.parseInt(ctx.NUMCONST().getText()));
        } else if (ctx.CHARCONST() != null) {
            node = new CharConstant(ctx.CHARCONST().getText().charAt(0));
        } else if (ctx.STRINGCONST() != null) {
            node = new StringConstant(ctx.STRINGCONST().getText());
        } else {
            node = new BoolConstant(ctx.getText().equals("true"));
        }
        return node;
    }

    // TODO Uncomment and implement whatever methods make sense
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitDeclaration(CminusParser.DeclarationContext ctx) {
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitVarDeclId(CminusParser.VarDeclIdContext ctx) {
//        LOGGER.fine("visit VarDeclId");
        return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitTypeSpecifier(CminusParser.TypeSpecifierContext ctx) {
        return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitParam(CminusParser.ParamContext ctx) {
        return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitParamId(CminusParser.ParamIdContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitStatement(CminusParser.StatementContext ctx) {
//        LOGGER.fine("visit statement");
//        List<ParseTree> children = ctx.children;
//        for (ParseTree child : children) {
////            System.out.println(child.getChild(0).getText());
//        }
        return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitCompoundStmt(CminusParser.CompoundStmtContext ctx) {
        symbolTable = symbolTable.createChild();

        List<VarDeclaration> varDeclarations = new ArrayList<>();
        List<Statement> statements = new ArrayList<>();
        for (CminusParser.VarDeclarationContext d : ctx.varDeclaration()) {
            varDeclarations.add((VarDeclaration) visitVarDeclaration(d));
        }
        for (CminusParser.StatementContext s : ctx.statement()) {
            statements.add((Statement) visitStatement(s));
        }
        symbolTable = symbolTable.getParent();
        return new CompoundStatement(statements, varDeclarations);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitExpressionStmt(CminusParser.ExpressionStmtContext ctx) {

        Expression expression = (Expression) visitExpression(ctx.expression());
        return new ExpressionStatement(expression);

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitIfStmt(CminusParser.IfStmtContext ctx) {
        OrExpression orExpression = (OrExpression) visitSimpleExpression(ctx.simpleExpression());
        List<Statement> statements = new ArrayList<>();
        for (CminusParser.StatementContext s : ctx.statement()) {
            statements.add((Statement) visitStatement(s));
        }
        return new IfStatement(orExpression,statements);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitWhileStmt(CminusParser.WhileStmtContext ctx) {
        OrExpression expression = (OrExpression) visitSimpleExpression(ctx.simpleExpression());
        Statement statement = (Statement) visitStatement(ctx.statement());
        return new WhileStatement(expression, statement);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitBreakStmt(CminusParser.BreakStmtContext ctx) { return new BreakStatement(); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitExpression(CminusParser.ExpressionContext ctx) {

        if (ctx.simpleExpression() != null) {
            return visitSimpleExpression(ctx.simpleExpression());
        }
        if (ctx.expression() == null) {
            return new BinaryOperator((Expression) visitMutable(ctx.mutable()),ctx.getChild(1).getText(), null );
        }

        return new BinaryOperator((Mutable) visitMutable(ctx.mutable()),ctx.getChild(1).getText(), (Expression) visitExpression(ctx.expression()) );
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitSimpleExpression(CminusParser.SimpleExpressionContext ctx) {
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitOrExpression(CminusParser.OrExpressionContext ctx) {
        ArrayList<AndExpression> ands = new ArrayList<>();
        for (CminusParser.AndExpressionContext a : ctx.andExpression()) {
            ands.add((AndExpression) visitAndExpression(a));
        }

        return new OrExpression(ands);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitAndExpression(CminusParser.AndExpressionContext ctx) {
        List<UnaryRelExpression> unaryRelExpressions = new ArrayList<>();
        for (CminusParser.UnaryRelExpressionContext u : ctx.unaryRelExpression()) {
            unaryRelExpressions.add((UnaryRelExpression) visitUnaryRelExpression(u));
        }
        return new AndExpression(unaryRelExpressions);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitUnaryRelExpression(CminusParser.UnaryRelExpressionContext ctx) {
        return new UnaryRelExpression(ctx.BANG(), (RelExpression) visitChildren(ctx));
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitRelExpression(CminusParser.RelExpressionContext ctx) {
        ArrayList<SumExpression> sumExpressions = new ArrayList<>();
        ArrayList<String> relops = new ArrayList<>();
        for (CminusParser.SumExpressionContext s : ctx.sumExpression()) {
            sumExpressions.add((SumExpression) visitSumExpression(s));
        }
        for (CminusParser.RelopContext relop : ctx.relop()) {
            relops.add(relop.getText());
        }
        return new RelExpression(sumExpressions, relops);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitRelop(CminusParser.RelopContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitSumExpression(CminusParser.SumExpressionContext ctx) {
        ArrayList<String> sumops = new ArrayList<>();
        ArrayList<TermExpression> termExpressions = new ArrayList<>();

        for (CminusParser.SumopContext s : ctx.sumop()) {
            sumops.add(s.getText());
        }

        for (CminusParser.TermExpressionContext t : ctx.termExpression()) {
            termExpressions.add((TermExpression) visitTermExpression(t));
        }

        return new SumExpression(termExpressions, sumops);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitSumop(CminusParser.SumopContext ctx) {
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitTermExpression(CminusParser.TermExpressionContext ctx) {
        ArrayList<UnaryExpression> unaryExpressions = new ArrayList<>();


        ArrayList<String> mulops = new ArrayList<>();

        for (CminusParser.UnaryExpressionContext u : ctx.unaryExpression()) {
            unaryExpressions.add((UnaryExpression) visitUnaryExpression(u));
        }

        for (CminusParser.MulopContext m : ctx.mulop()) {
            mulops.add(m.getText());
        }
        return new TermExpression(unaryExpressions, mulops);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitMulop(CminusParser.MulopContext ctx) {

        return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitUnaryExpression(CminusParser.UnaryExpressionContext ctx) {
        Factor factor = (Factor) visitFactor(ctx.factor());
        List<String> unaryops = new ArrayList<>();
        for (CminusParser.UnaryopContext u : ctx.unaryop()) {
            unaryops.add(u.getText());
        }

        return new UnaryExpression(unaryops, factor);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitUnaryop(CminusParser.UnaryopContext ctx) {
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitFactor(CminusParser.FactorContext ctx) {
        if (ctx.immutable() != null) {
            return new Factor((Immutable) visitImmutable(ctx.immutable()), null);
        }
        return  new Factor(null , (Mutable) visitMutable(ctx.mutable()));
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitMutable(CminusParser.MutableContext ctx) {
        String id = ctx.ID().getText();
        SymbolInfo findSymbol = symbolTable.find(id);
        if (findSymbol == null) {
            LOGGER.warning("Undefined symbol on line " + ctx.getStart().getLine() + ": " + id);
        }
        if (ctx.expression() != null) {
            return new Mutable(ctx.ID().getText(), (Expression) visitExpression(ctx.expression()));
        }
        return new Mutable(ctx.ID().getText(), null);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitImmutable(CminusParser.ImmutableContext ctx) {
        Expression expression = null;
        if (ctx.expression() != null) {
            expression = (Expression) visitExpression(ctx.expression());
        }
        Call call = null;
        if (ctx.call()!=null) {
            call = (Call) visitCall(ctx.call());
        }
        String constant = ctx.getText();
        return new Immutable(expression, call, constant);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Node visitCall(CminusParser.CallContext ctx) {
        String id = ctx.getChild(0).getText();
        SymbolInfo findSymbol = symbolTable.find(id);
        if (findSymbol == null) {
           LOGGER.warning("Undefined symbol on line " + ctx.getStart().getLine() + ": " + id);
        }
        ArrayList<Expression> expressions = new ArrayList<>();
        for (CminusParser.ExpressionContext e : ctx.expression()) {
            expressions.add((Expression) visitExpression(e));
        }
        return new Call(id, expressions);
    }

}
