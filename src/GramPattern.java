import java.util.*;

public class GramPattern {

    private Stack<Character> stack;

    private Map<Integer,Statement> finalExp;

    public Map<Integer,Statement> getFinalExp() {
        return finalExp;
    }

    public void setFinalExp(Map<Integer,Statement> finalExp) {
        this.finalExp = finalExp;
    }

    private Map<Character,List<Character>> mapper;

    public Map<Character, List<Character>> getMapper() {
        return mapper;
    }

    public void setMapper(Map<Character, List<Character>> mapper) {
        this.mapper = mapper;
    }

    private List<String> jumpTable;

    private String[] expressions;

    public List<String> getJumpTable() {
        return jumpTable;
    }

    public void setJumpTable(List<String> jumpTable) {
        this.jumpTable = jumpTable;
    }

    private Map<Character,Map<Character,Integer>> matchTable;

    private Set<Character> terminals;

    private Set<Character> nonTerminals;

    private Map<Character,Set<Character>> first;

    private Map<Character,Set<Character>> firstTemp;

    public Map<Character,Set<Character>> getFirstTemp() {
        return firstTemp;
    }

    public void setFirstTemp(Map<Character,Set<Character>> firstTemp) {
        this.firstTemp = firstTemp;
    }

    public Map<Character,Map<Character,Integer>> getMatchTable() {
        return matchTable;
    }

    public void setMatchTable(Map<Character,Map<Character,Integer>> matchTable) {
        this.matchTable = matchTable;
    }

    public GramPattern(String[] expressions) {
        this.expressions = expressions;
        this.stack=new Stack<>();
    }

    public Stack<Character> getStack() {
        return stack;
    }

    public void setStack(Stack<Character> stack) {
        this.stack = stack;
    }

    public String[] getExpressions() {
        return expressions;
    }

    public Set<Character> getTerminals() {
        return terminals;
    }

    public Set<Character> getNonTerminals() {
        return nonTerminals;
    }

    public Map<Character,Set<Character>> getFirst() {
        return first;
    }

    public void setExpressions(String[] expressions) {
        this.expressions = expressions;
    }

    public void setTerminals(Set<Character> terminals) {
        this.terminals = terminals;
    }

    public void setNonTerminals(Set<Character> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    public void setFirst(Map<Character,Set<Character>> first) {
        this.first = first;
    }
}
