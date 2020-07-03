package com.chaturv.fnf;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PricePointAnalysis {

    static Set<Integer> QUANTITIES = Stream.of(5, 10, 15).collect(Collectors.toSet());
    static List<List<Integer>> qtyCombinations = new ArrayList<>();

    void analyze(Set<Product> products) {
        products.forEach(System.out::println);
        printLine();
        IntStream.range(1, 3).forEach( i -> {
                    Set<Set<Product>> productCombinations = Sets.combinations(products, i);
                    findQtyCombinations(QUANTITIES, i, new ArrayList<>());

                    for (Set<Product> productCombination : productCombinations) {
                        List<Product> tempPC = new ArrayList<>(productCombination);
                        StringBuilder sb = new StringBuilder();
                        for (List<Integer> qtyCombination : qtyCombinations) {
                            for (i = 0; i < tempPC.size(); i++) {
                                Product product = tempPC.get(i);
                                Integer qty = qtyCombination.get(i);

                                sb.append(String.format("ProductID=[%s], Qty=[%d] | ", product.getId(), qty));

                                String msg = String.format("ProductID [%s], Qty=[%d], Profit=[%.2f]", product.getId(), qty, product.getProfit() * qty);
                                System.out.println(msg);
                            }
                        }
                    }
                    qtyCombinations.clear();
                    printLine();
                }
        );
    }

    private void printLine() {
        IntStream.range(0, 81).forEach(i -> System.out.print("-"));
        System.out.println();
    }

    private static void findQtyCombinations(Set<Integer> quantities, Integer n, List<Integer> sol) {
        if (sol.size() == n) { //stop condition for the recursion
            //System.out.println(sol);
            qtyCombinations.add(new ArrayList<>(sol));
            return;
        }
        for (Integer quantity : quantities) {
            sol.add(quantity);
            findQtyCombinations(quantities, n, sol); //recursive call
            sol.remove(sol.size() - 1); //cleaning up environment
        }
    }
}
