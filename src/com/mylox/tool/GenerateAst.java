//> Representing Code generate-ast
package com.mylox.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GenerateAst {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("We like trailing slashes! ");
            System.err.println("Usage: generate_ast <output directory>/");
            System.exit(64);
        }

        String outputDir = args[0];

        final String stmtCN = "Stmt";
        PrintWriter statementWriter = new PrintWriter(outputDir+stmtCN+".java");
        List<String> statements = Arrays.asList(
                "Block      : List<Stmt> statements",
                "Expression : Expr expression",
                "If         : Expr condition, Stmt thenBranch, Stmt elseBranch",
                "While      : Expr condition, Stmt body",
                "Print      : Expr expression",
                "Var        : Token name, Expr initializer"
        );

        final String exprCN = "Expr";
        PrintWriter expressionWriter = new PrintWriter(outputDir+exprCN+".java");
        List<String> expressions = Arrays.asList(
                "Assign   : Token name, Expr value",
                "Binary   : Expr left, Token operator, Expr right",
                "Logical  : Expr left, Token operator, Expr right",
                "Unary    : Token operator, Expr right",
                "Grouping : Expr expression",
                "Literal  : Object value",
                "Variable : Token name"
        );

        Generator.generate(expressionWriter, exprCN, expressions);
        Generator.generate(statementWriter, stmtCN, statements);
    }
}