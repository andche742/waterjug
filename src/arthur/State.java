package arthur;
import java.util.*;
public class State {
    int a, b, c;
    
    State(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return this.a == state.a && this.b == state.b && this.c == state.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "[" + a + ", " + b + ", " + c + "]";
    }
}