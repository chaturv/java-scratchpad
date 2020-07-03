package com.chaturv.fnf;

import com.google.common.collect.Sets;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PricePointAnalysis {

    static Set<Integer> QUANTITIES = Stream.of(5, 10, 15).collect(Collectors.toSet());
    static List<List<Integer>> qtyCombinations = new ArrayList<>();

    void analyze(Set<Product> products) throws IOException {
        products.forEach(System.out::println);
        printLine();

        FileWriter fileWriter = new FileWriter("C:/work/fnf_price_analysis.csv");
        fileWriter.write("Product Combination, Total Sales Price, Total Profit");
        fileWriter.write("\n");

        IntStream.range(1, products.size() + 1).forEach( i -> {
                    Set<Set<Product>> productCombinations = Sets.combinations(products, i);
                    findQtyCombinations(QUANTITIES, i, new ArrayList<>());

                    for (Set<Product> productCombination : productCombinations) {
                        List<Product> tempPC = new ArrayList<>(productCombination);

                        for (List<Integer> qtyCombination : qtyCombinations) {
                            StringBuilder sb = new StringBuilder();
                            double totalProfit = 0;
                            double totalSalesPrice = 0;

                            for (i = 0; i < tempPC.size(); i++) {
                                Product product = tempPC.get(i);
                                Integer qty = qtyCombination.get(i);

                                sb.append(String.format("ProductID=[%s] Qty=[%d]; ", product.getId(), qty));
                                totalProfit += product.getProfit() * qty;
                                totalSalesPrice += product.getSalesPrice() * qty;
                            }
                            sb.append(",").append(totalSalesPrice).append(",").append(String.format("%.2f", totalProfit));
                            try {
                                fileWriter.write(sb.toString());
                                fileWriter.write("\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    System.out.println("Completed productCombination: " + i);
                    qtyCombinations.clear();
                    printLine();
                }
        );
        fileWriter.close();
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
