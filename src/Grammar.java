import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;

public class Grammar {

    private static void getNT(GramPattern pattern){
        Set<Character> nonTerminals=new HashSet<>();
        for (String expression:pattern.getExpressions()) {
            nonTerminals.add(expression.split("->")[0].charAt(0));
        }
        pattern.setNonTerminals(nonTerminals);
    }

    private static void getT(GramPattern pattern){
        Set<Character> terminals=new HashSet<>();
        Map<Character,Set<Character>> map=new HashMap<>();
        for (String expression:pattern.getExpressions()){
            String str=expression.split("->")[1];
            Character non=expression.split("->")[0].charAt(0);
            String[] temp=str.split("\\|");
            Set<Character> set=new HashSet<>();
            for (String t:temp){
                for(int i=0;i<t.length();i++){
                    Character c=t.charAt(i);
                    if (i==0){
                        set.add(c);
                    }
                    if (!pattern.getNonTerminals().contains(t.charAt(i))){
                        terminals.add(t.charAt(i));
                    }
                }
            }
            map.put(non,set);
        }
        pattern.setFirstTemp(map);
        pattern.setTerminals(terminals);
    }

    private static void getFirst(GramPattern pattern){
        Set<Character> terminals=pattern.getTerminals();
        Set<Character> nonTerminals=pattern.getNonTerminals();
        Map<Character,Set<Character>> first=new HashMap<>();
        for (Character c:nonTerminals){
            Set<Character> list=new HashSet<>();
            first.put(c,list);
        }
        Map<Character,Set<Character>> firstTemp=pattern.getFirstTemp();
        boolean isChange=true;
        while (isChange){
            isChange=false;
            for (Map.Entry<Character,Set<Character>> entry:firstTemp.entrySet()){
                Set<Character> res=new HashSet<>();
                Character pre=entry.getKey();
                Set<Character> match=entry.getValue();
                for (Character c:match){
                    if (terminals.contains(c)){
                        res.add(c);
                    }else {
                        first.get(pre).addAll(first.get(c));
                    }
                    int size=first.get(pre).size();
                    first.get(pre).addAll(res);
                    if (first.get(pre).size()!=size){
                        isChange=true;
                    }
                }
            }
        }
        pattern.setFirst(first);
    }

    /**
     *
     * @param pattern
     * S(letter)    s,t,g,w(firstMatchers)
     */
    private static void fillMatchTable(GramPattern pattern){
        Set<Character> terminals=pattern.getTerminals();
        Set<Character> nonTerminals=pattern.getNonTerminals();
        Map<Character,Set<Character>> first=pattern.getFirst();
        Map<Character,Map<Character,Integer>> table;
        for (Map.Entry<Character,Set<Character>> entry:first.entrySet()){
            Character letter=entry.getKey();
            Map<Character,Integer> map=new HashMap<>();
            Set<Character> firstMatches=entry.getValue();
            for (Character character:firstMatches){

            }
        }
//        pattern.setMatchTable(tables);
    }

    public static void main(String[] args) {
//        String[] exps={"E->TX","X->+TX|@","T->FY","Y->*FY|@","F->(E)|i"};
        String[] exps={"S->NVN","N->s|t|g|w","v->e|d"};
        GramPattern pattern=new GramPattern(exps);
        getNT(pattern);
        getT(pattern);
        pattern.getTerminals().forEach(System.out::println);
        System.out.println("======");
        pattern.getNonTerminals().forEach(System.out::println);
        System.out.println("======");
        for (Map.Entry<Character,Set<Character>> entry:pattern.getFirstTemp().entrySet()){
            Set<Character> set=entry.getValue();
            System.out.print(entry.getKey()+":");
            set.forEach(System.out::print);
            System.out.println();
        }
        System.out.println("======");
        getFirst(pattern);
        Map<Character,Set<Character>> first=pattern.getFirst();
        for (Map.Entry<Character,Set<Character>> entry:first.entrySet()){
            Set<Character> set=entry.getValue();
            System.out.print(entry.getKey()+":");
            set.forEach(System.out::print);
            System.out.println();
        }
//        fillMatchTable(pattern);
        String statement="gdw";

    }
}
