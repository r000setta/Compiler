import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Patterns {
    private static String[] KEY_WORDS={"int","float","double","boolean"};
    public static char END=';';
    private static String[] OP_CODES={"+","-","*","/","="};
    private List<String> opCodes;
    private List<String> keyWords;

    Patterns(){
        this.opCodes =new ArrayList<>();
        this.keyWords =new ArrayList<>();
        fill(opCodes,OP_CODES);
        fill(keyWords,KEY_WORDS);
    }

    private static void fill(List<String> list,String[] strings){
        list.addAll(Arrays.asList(strings));
    }

    public List<String> getOpCodes() {
        return opCodes;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }
}