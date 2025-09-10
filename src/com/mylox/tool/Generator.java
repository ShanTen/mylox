package com.mylox.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generator {

    private static List<String> expressions;
    private static String className;

    public static void generate(PrintWriter writer, String className, List<String> expressions) throws IOException
    {
        Generator.expressions = expressions;
        Generator.className = className;
        cringe(writer);
    }


    // "Binary   : Expr left, Token operator, Expr right" => ["Binary", "Expr left", "Token operator", "Expr right"]
    private static List<String> processExpressionString(String exprStr) {
        String[] colonSplit = exprStr.split(":");
        String type = colonSplit[0].trim();
        String fields = colonSplit[1].trim();

        List<String> result = new ArrayList<>();
        result.add(type);
        for (String field : fields.split(",")) {
            result.add(field.trim());
        }

        return result;
    }

    private static void cringe(PrintWriter writer) throws IOException {
        writeImportsAndHeader(writer);
        writeAbstractClass(writer);
        writer.close();
    }

    private static void writeAbstractClass(PrintWriter writer) {
        writer.println(STR."abstract class \{className} {");
        writer.println();
        writeInterfaceVisitor(writer);
        writer.println();
        writeVisitorEntryMethod(writer); // accept method
        writer.println();
        writeExpressionClasses(writer); // template class instances
        writer.println();
        writer.println("}"); // end abstract class
    }

    private static void writeExpressionClasses(PrintWriter writer) {
        for (String expression : expressions) {
            List<String> parts = processExpressionString(expression);
            String exprType = parts.get(0);
            List<String> arguments = parts.subList(1, parts.size());

            writer.println();
            writer.println(STR."    static class \{exprType} extends \{className} {");
            writeConstructor(writer, exprType, arguments);
            writer.println();
            writeOverridenAcceptMethod(writer, exprType);
            writeFinalAffirmations(writer, arguments);
            writer.println("    }"); // end of the static class
        }
    }

    private static void writeFinalAffirmations(PrintWriter writer, List<String> arguments) {
        writer.println();
        for (String arg : arguments) {
            writer.println(STR."        final \{arg};");
        }
    }

    private static void writeOverridenAcceptMethod(PrintWriter writer, String exprType) {
        writer.println("        @Override");
        writer.println("        <R> R accept(Visitor<R> visitor) {");
        writer.println(STR."            return visitor.visit\{exprType}\{className}(this);");
        writer.println("        }");
    }

    private static void writeConstructor(PrintWriter writer, String exprType, List<String> arguments) {
        // constructor signature
        writer.print(STR."        \{exprType}(");
        for (int i = 0; i < arguments.size(); i++) {
            String arg = arguments.get(i);
            if (i == arguments.size() - 1)
                writer.print(STR."\{arg}");
            else
                writer.print(STR."\{arg}, ");
        }
        writer.println(") {"); // open constructor body

        // assignments
        for (String arg : arguments) {
            String[] tokens = arg.trim().split("\\s+");
            String attribute = tokens[tokens.length - 1]; // last token is the variable name
            writer.println(STR."            this.\{attribute} = \{attribute};");
        }

        writer.println("        }"); // close constructor
    }

    private static void writeVisitorEntryMethod(PrintWriter writer) {
        writer.println("    abstract <R> R accept(Visitor<R> visitor);");
    }

    private static void writeInterfaceVisitor(PrintWriter writer) {
        writer.println("    interface Visitor<R> {");
        for (String fullExpr : expressions) {
            String exprType = processExpressionString(fullExpr).get(0);
            writeVisitMethod(writer, exprType);
        }
        writer.println("    }");
    }

    private static void writeVisitMethod(PrintWriter writer, String exprType) {
        writer.println(STR."        R visit\{exprType}\{className}(\{exprType} expr);");
    }

    private static void writeImportsAndHeader(PrintWriter writer) {
//        writer.println("package com.playpen.sandpit;");
        writer.println("package com.mylox.lox;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println();
    }
}

