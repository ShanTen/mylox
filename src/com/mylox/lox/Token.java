package com.mylox.lox;

/*
* What is a lexeme
*   A lexeme is the string notation of a toke n
* */

public class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line){
        this.lexeme = lexeme;
        this.type = type;
        this.literal = literal;
        this.line = line;
    }

    public String toString() {
        return STR."\{type} \{lexeme} \{literal}";
    }
}
