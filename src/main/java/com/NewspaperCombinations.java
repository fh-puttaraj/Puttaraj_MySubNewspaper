package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NewspaperSubscription {
    private String name;
    private double[] dailyPrices;

    public NewspaperSubscription(String name, double[] dailyPrices) {
        this.name = name;
        this.dailyPrices = dailyPrices;
    }

    public String getName() {
        return name;
    }

    public double getWeeklyCost() {
        double weeklyCost = 0;
        for (double price : dailyPrices) {
            weeklyCost += price;
        }
        return weeklyCost;
    }
}

public class NewspaperCombinations {
    private static List<NewspaperSubscription> newspaperSubscriptions = new ArrayList<>();

    public static void main(String[] args) {
        initializeNewspapers();

        double weeklyBudget = 40;
        List<List<String>> validCombinations = calculateCombinations(weeklyBudget);

        printCombinations(validCombinations);
    }

    private static void initializeNewspapers() {
        newspaperSubscriptions.add(new NewspaperSubscription("TOI", new double[]{3, 3, 3, 3, 3, 5, 6}));
        newspaperSubscriptions.add(new NewspaperSubscription("Hindu", new double[]{2.5, 2.5, 2.5, 2.5, 2.5, 4, 4}));
        newspaperSubscriptions.add(new NewspaperSubscription("ET", new double[]{4, 4, 4, 4, 4, 4, 10}));
        newspaperSubscriptions.add(new NewspaperSubscription("BM", new double[]{1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5}));
        newspaperSubscriptions.add(new NewspaperSubscription("HT", new double[]{2, 2, 2, 2, 2, 4, 4}));
    }

    private static List<List<String>> calculateCombinations(double budget) {
        List<List<String>> validCombinations = new ArrayList<>();

        for (int comboSize = 1; comboSize <= newspaperSubscriptions.size(); comboSize++) {
            List<List<String>> combos = combinations(newspaperSubscriptions.size(), comboSize);
            for (List<String> combo : combos) {
                double totalCost = 0;
                for (String newspaper : combo) {
                    for (NewspaperSubscription subscription : newspaperSubscriptions) {
                        if (subscription.getName().equals(newspaper)) {
                            totalCost += subscription.getWeeklyCost();
                            break;
                        }
                    }
                }
                if (totalCost <= budget) {
                    validCombinations.add(combo);
                }
            }
        }
        return validCombinations;
    }

    private static List<List<String>> combinations(int n, int k) {
        List<List<String>> result = new ArrayList<>();
        combinationsUtil(new String[n], 0, k, 0, result);
        return result;
    }

    private static void combinationsUtil(String[] data, int start, int k, int index, List<List<String>> result) {
        if (index == k) {
            result.add(new ArrayList<>(Arrays.asList(data).subList(0, k)));
            return;
        }

        for (int i = start; i < data.length; i++) {
            data[index] = newspaperSubscriptions.get(i).getName();
            combinationsUtil(data, i + 1, k, index + 1, result);
        }
    }

    private static void printCombinations(List<List<String>> combinations) {
        int count = 1;
        for (List<String> combination : combinations) {
            System.out.println("Combination " + count + ": " + combination);
            count++;
        }
    }
}
