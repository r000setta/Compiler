package ezstack;

//test
public class Main {
    public static void main(String[] args) {
        TokenManager manager=new TokenManager("bcbdce");
        GramAnalyzer analyzer=new GramAnalyzer(manager);
        analyzer.parse();
    }
}