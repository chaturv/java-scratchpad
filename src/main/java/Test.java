import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    static Set<Integer> QUANTITIES = Stream.of(5, 10, 15).collect(Collectors.toSet());
    static ArrayList<List<Integer>> collect = new ArrayList<>();

    /**
     * @param args
     */
    public static void main(String[] args) {

        findCombinations(QUANTITIES, 2, new ArrayList<>());
        collect.forEach(System.out::println);
    }

    private static void findCombinations(Set<Integer> quantities, Integer n, List<Integer> sol) {
        if (sol.size() == n) { //stop condition for the recursion
            //System.out.println(sol);
            collect.add(new ArrayList<>(sol));
            return;
        }
        for (Integer quantity : quantities) {
            sol.add(quantity);
            findCombinations(quantities, n, sol); //recursive call
            sol.remove(sol.size() - 1); //cleaning up environment
        }
    }
}