# MyLox

An interpreted programming language + parser.

## Motivation

I've long been fascinated with building lexers and parsers. When I began learning Java seriously, the idea of writing a simple programming language was both a challenge and an exciting opportunity. Inspired by Bob Nystrom's book *Crafting Interpreters*, I decided to build **MyLox**, my own version of the Lox language, adapted with a few personal choices and experiments.

## Divergences from the Book

While based on *Crafting Interpreters*, **MyLox** diverges in a few notable ways:

### 1. Cleaner `Expr` and `Stmt` Generator

The book's approach to generating AST classes via a handwritten generator script felt outdated - especially with Java 21’s upcoming support for [template strings](https://openjdk.org/jeps/430). I rewrote the AST generator for clarity, readability, and maintainability. It produces cleaner code and is easier to modify if needed.

### 2. Implicit String Conversion (JavaScript-style)

Inspired by JavaScript, I added basic implicit type coercion to support string concatenation and conversions in a more forgiving way. This is admittedly a temporary hack — until I implement proper template strings — but it makes the interpreter more playful and fun to experiment with.

### 3. Simpler Syntax

MyLox's syntax has been tweaked to be more concise and beginner-friendly in places. Some constructs may deviate slightly from canonical Lox, either for experimentation or personal taste.

## Current Capabilities

- Expression evaluation: arithmetic, logical, comparison
- Variables and scoping
- Control flow: `if`, `else`, `while`, `for`
- Functions and closures
- String manipulation with implicit coercion
- REPL and script execution modes

## Getting Started

### Prerequisites

- Java 17+ recommended (some features may target newer versions)
- (Optional) Maven or Gradle for building

### Running MyLox

```bash
javac -d out src/com/mylox/*.java
java -cp out com.mylox.Lox             # Starts REPL
java -cp out com.mylox.Lox demo.mylox    # Runs a script
```

## Directory Structure

```
src/com/mylox/
├── Lox.java              # Entry point
├── Scanner.java          # Lexer
├── Parser.java           # AST builder
├── Interpreter.java      # Evaluator
├── Resolver.java         # Static analysis
├── Expr.java / Stmt.java # AST class hierarchy
├── AstPrinter.java       # Debug printer
├── AstGenerator.java     # Custom AST code generator
...
```

## Future Plans

- [ ] Proper template string support (Java 21+)
- [ ] Support for classes and inheritance 

Nothing apart from these because this is a very low returns for effort project and the only reason I've done even this much is because I was extremely delusional and free.

## Acknowledgements

- **[Crafting Interpreters](https://craftinginterpreters.com)** by Bob Nystrom - the primary inspiration for this project


## License

MIT License
