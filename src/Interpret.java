
import java.util.Deque;

public class Interpret {

    private String input;
    private Lexer lexer;
    private Deque<Token> deque;
    private Token currentToken;

    public Interpret(String input) throws InterpretException {
        this.input = input;
        this.lexer = new Lexer(input);
        this.deque = lexer.tokenize();
        this.currentToken = deque.poll();
    }

    private void eat(Type type) throws InterpretException {
        if(currentToken != null && currentToken.getType() == type)
            currentToken = deque.poll();
        else throw new InterpretException("Types are not equal.");
    }

    private int factor() throws InterpretException {
        Token t = currentToken;
        if(deque.size() > 0)
            eat(Type.INTEGER);
        return (int) t.getValue();
    }

    private int term() throws InterpretException {
        int result = factor();
        while (currentToken.getType() != null && currentToken.getType() == Type.MULT || currentToken.getType() == Type.DIV)
        {
            if(currentToken.getType() == Type.MULT) {
                eat(Type.MULT);
                result *= factor();
            }
            else if(currentToken.getType() == Type.DIV)
            {
                eat(Type.DIV);
                result /= factor();
            }
        }
        return result;
    }

    int expr() throws InterpretException {
        int result = term();
        while (currentToken.getType() == Type.PLUS || currentToken.getType() == Type.MINUS)
        {
            if(currentToken.getType() == Type.PLUS)
            {
                eat(Type.PLUS);
                result += term();
            }
            else if(currentToken.getType() == Type.MINUS)
            {
                eat(Type.MINUS);
                result -= term();
            }
        }
        return result;
    }
}
