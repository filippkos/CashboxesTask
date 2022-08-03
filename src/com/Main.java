package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    //number of customers.
    private final static int CUSTOMERS = 6;

    //number of cash boxes.
    private final static int CASH_BOXES = 3;

    public static void main(String[] args) {
        System.out.println("Total time: " + totalTimeCalculation(CASH_BOXES, CUSTOMERS));
    }

    static int totalTimeCalculation(int cashBoxes, int customers) {
        int[] boxes = new int[cashBoxes];
        ArrayList<Integer> customersTimeArray = customersTimeToArray(customers);
        if (cashBoxes == 1) {
            return findTheAmountOfTime(customersTimeArray);
        }
        if (cashBoxes >= customers) {
            return findTheMaxTime(customersTimeArray);
        }
        if (cashBoxes > 1 && customers > cashBoxes) {
            boxes = uniformDistributionOfTime(boxes, customersTimeArray);
        }
        showMeCashBoxes(boxes);
        return Arrays.stream(boxes).max().getAsInt();
    }

    static int[] uniformDistributionOfTime(int[] boxes, ArrayList<Integer> customersTimeArray) {
        Collections.sort(customersTimeArray, Collections.reverseOrder());
        System.out.println(customersTimeArray);
        int min = 0;
        int pos = 0;

        for (int i = 0; i < customersTimeArray.size(); i++) {
            if (i <= boxes.length - 1) {
                boxes[i] += customersTimeArray.get(i);
                if (i == boxes.length - 1)
                    min = customersTimeArray.get(i);
                continue;
            } else {
                for (int j = 0; j < boxes.length; j++) {
                    min = Arrays.stream(boxes).min().getAsInt();
                    if (boxes[j] <= min) {
                        pos = j;
                    }
                }
                boxes[pos] += customersTimeArray.get(i);
            }
        }
        return boxes;
    }

    static int findTheAmountOfTime(ArrayList<Integer> customersTimeArray) {
        int totalTime = 0;
        for (int time : customersTimeArray) {
            totalTime += time;
        }
        return totalTime;
    }

    static int findTheMaxTime(ArrayList<Integer> customersTimeArray) {
        int currTime = 0;
        for (int time : customersTimeArray) {
            if (time >= currTime)
                currTime = time;
        }
        return currTime;
    }

    static ArrayList<Integer> customersTimeToArray(int quantity) {
        ArrayList<Integer> customersTime = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            customersTime.add(new Customer().getTimeAtTheCheckout());
        }
        return customersTime;
    }

    static void showMeCashBoxes(int[] boxes) {
        String timeDistribution = "Time distribution:\n" + "|";
        for (int box : boxes) {
            timeDistribution += box + "|";
        }
        System.out.println(timeDistribution);
    }


}
