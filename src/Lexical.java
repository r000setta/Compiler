import java.util.*;

public class Lexical {

    private static List<Token> analyze(String tokenStream) {
        List<Token> tokenList = new ArrayList<>();
        Patterns patterns=new Patterns();
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < tokenStream.length(); i++) {
            char c = tokenStream.charAt(i);
            if (c != ' ' && c!=Patterns.END) {
                temp.append(c);
            } else {
                String str = temp.toString();
                try {
                    Integer num = Integer.parseInt(str);
                    tokenList.add(new Token(str, Type.NUMBER));
                } catch (NumberFormatException e) {
                    if (patterns.getKeyWords().contains(str)) {
                        tokenList.add(new Token(str, Type.KEYWORDS));
                    } else if (patterns.getOpCodes().contains(str)) {
                        tokenList.add(new Token(str, Type.OPCODE));
                    } else {
                        tokenList.add(new Token(str, Type.VARIABLE));
                    }
                }
                temp = new StringBuffer();
            }
        }
        return tokenList;
    }

    public static void main(String[] args) {
        List<Token> list = analyze("int x = 1;float name = a;");
        for (Token token : list) {
            System.out.println(token.toString());
        }
    }
}
