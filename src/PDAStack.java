import java.util.*;

public class PDAStack extends Stack<Character> {

    public PDAStack() {
        super();
    }

    public PDAStack(PDAStack pdaStack) {
        this();
        for (Character symbol : pdaStack) {
            add(new Character(symbol.charValue()));
        }
    }

    public Character push(Character symbol) {
        if (symbol == null)
            throw new NullPointerException("symbol can not be null.");

        return symbol.equals('λ') ?
                'λ' :
                super.push(symbol);
    }

    public void push(String symbols) {
        for (int i = symbols.length() - 1; i >= 0; --i) {
            push(symbols.charAt(i));
        }
    }

    public Character pop() {
        return super.pop();
    }
}