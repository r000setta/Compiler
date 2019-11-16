import com.sun.org.apache.bcel.internal.generic.LUSHR;

import java.util.List;

public class Statement {

    private Character left;

    private String right;

//    private Integer id;

    public Character getLeft() {
        return left;
    }

    public void setLeft(Character left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
