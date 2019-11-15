public class Token {
    private Type type;

    private String name;

    public Token(String name,Type type) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}