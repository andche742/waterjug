import java.util.*;

public class State {
    int a, b, c;

    public State(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        State that = (State)o;

        if (this.a == that.a && this.b == that.b && that.c == this.c) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "[ " + this.a + ", " + this.b + ", " + this.c + "]";
    }
}