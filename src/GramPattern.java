import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GramPattern {
    private String[] expressions;

    private Character[][] matchTable;

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

    public Character[][] getMatchTable() {
        return matchTable;
    }

    public void setMatchTable(Character[][] matchTable) {
        this.matchTable = matchTable;
    }

    public GramPattern(String[] expressions) {
        this.expressions = expressions;
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
