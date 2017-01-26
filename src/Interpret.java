import java.math.BigDecimal;
import java.util.List;

public class Interpret {

    private String input;
    private Lexer lexer;
    private List<Token> deque;

    public Interpret(String input) throws InterpretException {
        this.input = input;
        this.lexer = new Lexer(input);
        this.deque = lexer.tokenize();
        factor();
    }

    private void factor()
    {
        for(Token x : deque)
            System.out.println(x.getValue());
    }

    public BigDecimal result()
    {
        return null;
    }

}
