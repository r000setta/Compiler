package ezstack;

import java.util.Stack;

/**
 *S->bScA
 * S->cbd
 * A->bcA
 * A->d
 */
public class GramAnalyzer {
    private TokenManager manager;
    private Stack<Character> stack;
    private char currentToken;

    public GramAnalyzer(TokenManager tokenManager){
        this.manager=tokenManager;
        advance();
        stack=new Stack<>();
        stack.push('$');
        stack.push('S');
    }

    private void advance(){
        currentToken=manager.getNextToken();
    }

    public void parse(){
        boolean done=false;
        while (!done){
            switch (stack.peek()){
                case 'S':
                    if (currentToken=='b'){
                        stack.pop();
                        stack.push('A');
                        stack.push('c');
                        stack.push('S');
                        advance();
                    }else if (currentToken=='c'){
                        stack.pop();
                        stack.push('d');
                        stack.push('b');
                        advance();
                    }else
                        done=true;
                    break;
                case 'A':
                    if (currentToken=='b'){
                        stack.push('c');
                    }else if (currentToken=='d'){
                        stack.pop();
                        advance();
                    }else
                        done=true;
                    break;
                case 'b':
                case 'c':
                case 'd':
                    if (stack.peek() ==currentToken){
                        stack.pop();
                        advance();
                    }else
                        done=true;
                    break;
                case '$':
                    done=true;
                    break;
            }
        }
        if (currentToken=='#' && stack.peek()=='$')
            System.out.println("Accept");
        else
            System.out.println("Reject");
    }

}