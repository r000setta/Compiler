import java.util.*;

public class Grammar {

    private static void getNT(GramPattern pattern) {
        Set<Character> nonTerminals = new HashSet<>();
        for (String expression : pattern.getExpressions()) {
            nonTerminals.add(expression.split("->")[0].charAt(0));
        }
        pattern.setNonTerminals(nonTerminals);
    }

    private static void getT(GramPattern pattern) {
        Set<Character> terminals = new HashSet<>();
        Map<Character, Set<Character>> map = new HashMap<>();
        for (String expression : pattern.getExpressions()) {
            String str = expression.split("->")[1];
            Character non = expression.split("->")[0].charAt(0);
            String[] temp = str.split("\\|");
            Set<Character> set = new HashSet<>();
            for (String t : temp) {
                for (int i = 0; i < t.length(); i++) {
                    Character c = t.charAt(i);
                    if (i == 0) {
                        set.add(c);
                    }
                    if (!pattern.getNonTerminals().contains(t.charAt(i))) {
                        terminals.add(t.charAt(i));
                    }
                }
            }
            map.put(non, set);
        }
        pattern.setFirstTemp(map);
        pattern.setTerminals(terminals);
    }

    private static void getFirst(GramPattern pattern) {
        //"E->TX","X->+TX|-TX","T->FY","Y->*FY|/FY","F->NZ","Z->^F","N->(E)|n|+n|-n"
        Set<Character> terminals = pattern.getTerminals();
        Set<Character> nonTerminals = pattern.getNonTerminals();
        Map<Character, Set<Character>> first = new HashMap<>();
        for (Character c : nonTerminals) {
            Set<Character> list = new HashSet<>();
            first.put(c, list);
        }
        Map<Character, Set<Character>> firstTemp = pattern.getFirstTemp();
        boolean isChange = true;
        while (isChange) {
            isChange = false;
            for (Map.Entry<Character, Set<Character>> entry : firstTemp.entrySet()) {
                Set<Character> res = new HashSet<>();
                Character pre = entry.getKey();
                Set<Character> match = entry.getValue();
                for (Character c : match) {
                    int size = first.get(pre).size();
                    if (terminals.contains(c)) {
                        res.add(c);
                    } else {
                        first.get(pre).addAll(first.get(c));
                    }
                    first.get(pre).addAll(res);
                    if (first.get(pre).size() != size) {
                        isChange = true;
                    }
                }
            }
        }
        pattern.setFirst(first);
    }

    private static void fillMatchTable(GramPattern pattern) {
        Set<Character> nonTerminals = pattern.getNonTerminals();
        Map<Character, Set<Character>> first = pattern.getFirst();
        Map<Character, Map<Character, Integer>> table = new HashMap<>();

        for (Map.Entry<Integer, Statement> entry : pattern.getFinalExp().entrySet()) {
            Map<Character, Integer> row = new HashMap<>();
            Integer id = entry.getKey();
            Statement statement = entry.getValue();
            Character left = statement.getLeft();
            String right = statement.getRight();
            Map<Character, Integer> map = new HashMap<>();
            table.putIfAbsent(left,map);
            map = table.get(left);
            if (nonTerminals.contains(right.charAt(0))) {
                Set<Character> firstSet = first.get(right.charAt(0));
                for (Character c : firstSet) {
                    map.put(c, id);
                }
            } else {
                map.put(right.charAt(0), id);
            }
        }
        pattern.setMatchTable(table);
    }

    private static void buildJumpTable(GramPattern pattern) {
        List<String> list = new ArrayList<>();
        String[] exps = pattern.getExpressions();
        for (String exp : exps) {
            exp = exp.split("->")[1];
            String[] strs = exp.split("\\|");
            if (strs.length == 1) {
                list.add(strs[0]);
            } else {
                list.addAll(Arrays.asList(strs));
            }
        }
        pattern.setJumpTable(list);
    }

    private static void buildMapper(GramPattern pattern) {
        Map<Character, List<Character>> res = new HashMap<>();
        for (String exp : pattern.getExpressions()) {
            Character letter = exp.split("->")[0].charAt(0);
            List<Character> list = new ArrayList<>();
            String string = exp.split("->")[1];
            string = string.replaceAll("\\|", "");
            for (Character c : string.toCharArray()) {
                list.add(c);
            }
            res.put(letter, list);
        }
        pattern.setMapper(res);
    }

    private static void buildFinalExp(GramPattern pattern) {
        Map<Integer, Statement> res = new HashMap<>();
        String[] exps = pattern.getExpressions();
        int count = 0;
        for (String exp : exps) {
            String[] splits = exp.split("->");
            Character letter = splits[0].charAt(0);
            //temp:s|t|g|z
            String[] temp = splits[1].split("\\|");
            for (String s : temp) {
                Statement statement = new Statement();
                statement.setLeft(letter);
                statement.setRight(s);
                res.put(count++, statement);
            }
        }
        pattern.setFinalExp(res);
    }

    private static boolean judge(GramPattern pattern, String str) {
        Stack<Character> stack = pattern.getStack();
        stack.push('S');
        int i = 0;
        while (!stack.isEmpty()&&i<str.length()) {
            Character character = str.charAt(i);
            Character top = stack.pop();
            if (pattern.getNonTerminals().contains(top)) {
                Integer id = pattern.getMatchTable().get(top).get(character);
                if (id == null) {
                    return false;
                }
                Statement statement = pattern.getFinalExp().get(id);
                StringBuilder builder = new StringBuilder(statement.getRight());
                String ready = builder.reverse().toString();
                for (Character c : ready.toCharArray()) {
                    stack.push(c);
                }
            } else {
                if (top == character) {
                    i++;
                }
            }
        }
        return i==str.length();
    }

    private static void print(GramPattern pattern) {
        System.out.println("======FinalExp======");
        for (Map.Entry<Integer, Statement> entry : pattern.getFinalExp().entrySet()) {
            Integer i = entry.getKey();
            Statement statement = entry.getValue();
            System.out.println(i + " : " + statement.toString());
        }
        System.out.println("======Terminals======");
        pattern.getTerminals().forEach(System.out::println);
        System.out.println("======NonTerminals======");
        pattern.getNonTerminals().forEach(System.out::println);
        System.out.println("======FirstTemp======");
        for (Map.Entry<Character, Set<Character>> entry : pattern.getFirstTemp().entrySet()) {
            Set<Character> set = entry.getValue();
            System.out.print(entry.getKey() + ":");
            set.forEach(System.out::print);
            System.out.println();
        }
        System.out.println("======FirstSet======");
        Map<Character, Set<Character>> first = pattern.getFirst();
        for (Map.Entry<Character, Set<Character>> entry : first.entrySet()) {
            Set<Character> set = entry.getValue();
            System.out.print(entry.getKey() + ":");
            set.forEach(System.out::print);
            System.out.println();
        }
        System.out.println("======MatchTable======");
        for (Map.Entry<Character, Map<Character, Integer>> entry : pattern.getMatchTable().entrySet()) {
            Character c = entry.getKey();
            Map<Character, Integer> map = entry.getValue();
            System.out.print(c + " : ");
            for (Map.Entry<Character, Integer> entry1 : map.entrySet()) {
                System.out.print(entry1.getKey() + "," + entry1.getValue() + " ");
            }
            System.out.println();
        }
        System.out.println("========================");
    }

    public static void main(String[] args) {
//        String[] exps={"E->TX","X->+TX|@","T->FY","Y->*FY|@","F->(E)|i"};
//        String[] exps = {"S->NVN", "N->s|t|g|w", "V->e|d"};
//        String[] exps = {"E->TX","X->+TX|-TX","T->FY","Y->*FY|/FY","F->NZ","Z->^F","N->(E)|n|+n|-n"};
        String[] exps = {"S->+SS", "S->*SS", "S->a"};
//        String[] exps={"Z->d|X|Y|Z","Y->c","X->Y|a"};
        GramPattern pattern = new GramPattern(exps);
        getNT(pattern);
        getT(pattern);
        buildFinalExp(pattern);
        getFirst(pattern);
        buildJumpTable(pattern);
        buildMapper(pattern);
        fillMatchTable(pattern);
        print(pattern);
        System.out.println(judge(pattern, "n+n"));
    }
}
