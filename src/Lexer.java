import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private char currentChar;
    private int pos;
    private String input;

    public Lexer(String input)
    {
        this.input = input.replaceAll(" ", "");
        this.pos = 0;
        this.currentChar = input.charAt(pos);
    }

    private void advance()
    {
        pos++;
        if(pos < input.length())
        {
            currentChar = input.charAt(pos);
        }
        else currentChar = 0;
    }

    private int multiDigit()
    {
        String s = "";
        while (Character.isDigit(currentChar))
        {
            s += currentChar;
            advance();
        }
        return Integer.parseInt(s);
    }

    private @Nullable Token getNextToken()
    {
        Token t = null;
        if(Character.isDigit(currentChar) && currentChar != 0) {
            t = new Token<>(Type.INTEGER, multiDigit());
            return t;
        }
        else if(currentChar != 0){
            switch (currentChar)
            {
                case ('+'):
                    t = new Token<>(Type.PLUS, currentChar);
                    advance();
                    return t;
                case ('-'):
                    t = new Token<>(Type.MINUS, currentChar);
                    advance();
                    return t;
                case ('*'):
                    t = new Token<>(Type.MULT, currentChar);
                    advance();
                    return t;
                case ('/'):
                    t = new Token<>(Type.DIV, currentChar);
                    advance();
                    return t;
            }
        }
        return t;
    }

    public List<Token> tokenize() throws InterpretException {
        List<Token> deque = new ArrayList<>();
        Token t = getNextToken();
        if(t != null)
            deque.add(t);
        else throw new InterpretException("Something wrong with input string");
        while ((t = getNextToken()) != null)
            deque.add(t);
        return deque;
    }
}