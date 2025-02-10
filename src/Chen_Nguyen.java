import java.util.*;

public class Chen_Nguyen {
    
    static int[] capacities = new int[3];
    static int[] start = new int[3];
    static List<Integer> parentCount;
    static List<State> seen;

    static List<State> enumerate(State parent) {
        List<State> children = new ArrayList<State>();
        int pour;

        if (parent.a < capacities[0]) {
            if (parent.b > 0) {
                pour = Math.min(capacities[0] - parent.a, parent.b);
                parent.a += pour;
                parent.b -= pour;
                children.add(new State(parent.a, parent.b, parent.c));
            }
            if (parent.c > 0) {
                pour = Math.min(capacities[0] - parent.a, parent.c);
                parent.a += pour;
                parent.c -= pour;
                children.add(new State(parent.a, parent.b, parent.c));
            }
            

        }
        
        return children;
    }

    static State pour(State parent, int from, int to) {
        if 
        return new State(parent.a, parent.b, parent.c);
    }

    
    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.print("Usage: java Chen_Nguyen <capacity-1> <capacity-2> <capacity-3> <startState-1> <startState-2> <startState-3>");
        }

        capacities[0] = Integer.parseInt(args[0]);
        capacities[1] = Integer.parseInt(args[1]);
        capacities[2] = Integer.parseInt(args[2]);

        start[0] = Integer.parseInt(args[3]);
        start[1] = Integer.parseInt(args[4]);
        start[2] = Integer.parseInt(args[5]);


        
    }
}
