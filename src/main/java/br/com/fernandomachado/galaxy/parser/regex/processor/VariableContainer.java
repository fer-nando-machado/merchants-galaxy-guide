package br.com.fernandomachado.galaxy.parser.regex.processor;

import br.com.fernandomachado.galaxy.parser.regex.pattern.TokenEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Container that associates {@link TokenEnum}s with a collection of {@link String} variables.
 */
public class VariableContainer {

    private Map<TokenEnum, List<String>> map;

    public VariableContainer() {
        this.map = new HashMap<>();
    }

    public void addVariable(TokenEnum token, String variable) {
        if (!token.isVariable()) {
            return;
        }
        if (!this.map.containsKey(token)) {
            this.map.put(token, new ArrayList<>());
        }
        this.map.get(token).add(variable);
    }

    public List<String> getVariables(TokenEnum token) {
        return this.map.get(token);
    }

}
