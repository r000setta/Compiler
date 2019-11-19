package ezstack;

public class TokenManager {
    private int index;
    String input;

    public TokenManager(String input) {
        this.input=input;
        index=0;
        System.out.println("Input is "+input);
    }

    public char getNextToken(){
        if (index<input.length())
            return input.charAt(index++);
        else
            return '#';
    }
}