package com.mylox.lox;

import java.util.Map;
import java.util.HashMap;

public class Environment {
    final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();

    // Zarg Constructor in case of top level environement (global env)
    Environment() {
        enclosing = null;
    }

    // for scoped variables
    Environment(Environment upperEnclosingEnv) {
        this.enclosing = upperEnclosingEnv;
    }

    // to actual get back the token name
    Object get(Token name){
        if(values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }

        // someone might be referencing a variable in a higher level scope
        if (enclosing != null) return enclosing.get(name);

        throw new RuntimeError(name, STR."Undefined variable '\{name.lexeme}'.");
    }

    // for implementation, we initially use a hashmap because everything is in the global scope 
    void define(String name,Object value){
        values.put(name, value);
    }

    public void assign(Token name, Object value) {
        if(values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, STR."Undefined variable '\{name.lexeme}'.");
    }
}
