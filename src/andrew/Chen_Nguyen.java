package andrew;
import java.util.*;

public class Chen_Nguyen {
    static int[] capacities;
    static Set<State> seen = new HashSet<State>();
    static HashMap<State, Integer> parentCounts = new HashMap<State, Integer>();
    static ArrayList<List<State>> levels = new ArrayList<List<State>>();

    static State pour(State parent, int to, int from) {
        int[] amounts = {parent.a, parent.b, parent.c};
        if (amounts[to] < capacities[to] && amounts[from] > 0) {
            int pour = Math.min(capacities[to] - amounts[to], amounts[from]);
            amounts[to] += pour;
            amounts[from] -= pour;
            return new State(amounts[0], amounts[1], amounts[2]);
        }
        else {
            return null;
        }
    }

    static void bfs(State parent) {
        Queue<State> queue = new LinkedList<State>();
        queue.add(parent);
        seen.add(parent);
        levels.add(List.of(parent));
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            HashMap<State, Integer> currentLevel = new HashMap<State, Integer>();
            
            for (int x = 0; x < size; x++) {
                State current = queue.poll();

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i != j) {
                            State child = pour(current, i, j);
                            if (child != null && !seen.contains(child)) {
                                currentLevel.put(child, currentLevel.getOrDefault(child, 0) + 1);
                            }
                        }
                    }
                }
            }
            queue.addAll(currentLevel.keySet());
            seen.addAll(currentLevel.keySet());
            parentCounts.putAll(currentLevel);
            levels.add(new ArrayList<State>(currentLevel.keySet()));

        }
    }

    public static void main(String[] args) {
        try {
            capacities = new int[]{Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])};
            State start = new State(Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            System.out.println("Capacity: {'Jug A': " + capacities[0] + ", 'Jug B': " + capacities[1] + ", 'Jug C': " + capacities[2] + "}.");
            System.out.println("Initial amount of water: {'Jug A': " + start.a + ", 'Jug B': " + start.b + ", 'Jug C': " + start.c + "}.");

            bfs(start);

            System.out.println("Step: New Measurements: New States");
            System.out.println("-------------------------------------------------------------");

            Set<Integer> seenMeasurements = new HashSet<>();

        for (int i = 0; i < levels.size() - 1; i++) {
            List<State> currentStep = levels.get(i);
            Set<Integer> newMeasurements = new TreeSet<>();
            StringBuilder newStates = new StringBuilder();

            for (State s : currentStep) {
                if (!seenMeasurements.contains(s.a)) newMeasurements.add(s.a);
                if (!seenMeasurements.contains(s.b)) newMeasurements.add(s.b);
                if (!seenMeasurements.contains(s.c)) newMeasurements.add(s.c);
                
                int parentCount = parentCounts.getOrDefault(s, 1);
                String parentLabel = "";
                if (parentCount > 1) {
                     parentLabel = " p" + parentCount;
                }
                newStates.append(s).append(parentLabel).append(", ");
            }

            if (newStates.length() > 2) {
                newStates.setLength(newStates.length() - 2);
            }

            seenMeasurements.addAll(newMeasurements);

            System.out.printf("%-4d: %-15s : %s\n", i, newMeasurements.toString().replaceAll("[\\[\\]]", ""), newStates);
        }

        System.out.println("-------------------------------------------------------------");

        }
        catch (Exception e) {
            System.out.println("Usage: java Chen_Nguyen <capacity-1> <capacity-2> <capacity-3> <initial-1> <initial-2> <initial-3>");
        }
    }
}