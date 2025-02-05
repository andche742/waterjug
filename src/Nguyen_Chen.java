import java.util.*;

public class Nguyen_Chen {

    static int[] capacities;
    static List<List<State>> steps = new ArrayList<>();
    static Set<State> visited = new HashSet<>();
    static Map<State, Integer> parentCounts = new HashMap<>();

    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.println("Enter 3 capacities and 3 intial amounts");
            return;
        }

        capacities = new int[]{Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])};
        State initialState = new State(Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
        System.out.println("Capacity: {'Jug A': " + capacities[0] + ", 'Jug B': " + capacities[1] + ", 'Jug C': " + capacities[2] + "}.");
        System.out.println("Initial amount of water: {'Jug A': " + initialState.a + ", 'Jug B': " + initialState.b + ", 'Jug C': " + initialState.c + "}.");

        bfs(initialState);
        printStates();  
    }

    static void bfs(State initialState) {
        Queue<State> queue = new LinkedList<>();
        queue.add(initialState);
        visited.add(initialState);
        steps.add(List.of(initialState));

        while (!queue.isEmpty()) {
            HashMap<State, Integer> currentLevel = new HashMap<State, Integer>();

            for (int i = 0, size = queue.size(); i < size; i++) {
                State current = queue.poll();

                for (int from = 0; from < 3; from++) {
                    for (int to = 0; to < 3; to++) {
                        if (from != to) {
                            State next = pour(current, from, to);
                            if (next != null && !visited.contains(next)) {
                                currentLevel.put(next, currentLevel.getOrDefault(next, 0) + 1);
                                queue.add(next);
                            }
                        }
                    }
                }
            }

            if (!currentLevel.isEmpty()) {
                steps.add(new ArrayList<State>(currentLevel.keySet()));
                visited.addAll(currentLevel.keySet());
                parentCounts.putAll(currentLevel);
            }
        }
    }

    static State pour(State current, int from, int to) {
        int[] amounts = {current.a, current.b, current.c};
        if (amounts[from] == 0 || amounts[to] == capacities[to]) return null;

        int pourAmount = Math.min(amounts[from], capacities[to] - amounts[to]);
        amounts[from] -= pourAmount;
        amounts[to] += pourAmount;

        return new State(amounts[0], amounts[1], amounts[2]);
    }

    static void printStates() {
        System.out.println("Step: New Measurements: New States");
        System.out.println("-------------------------------------------------------------");

        Set<Integer> seenMeasurements = new HashSet<>();

        for (int i = 0; i < steps.size(); i++) {
            List<State> currentStep = steps.get(i);
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
}