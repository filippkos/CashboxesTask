package com;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    private final static int CUSTOMERS = 6;
    private final static int CASHBOXES = 3;

    public static void main(String[] args) {
        System.out.println(totalTimeCalculation(CASHBOXES,CUSTOMERS));
    }

    static int totalTimeCalculation(int cashboxes, int customers) {
        int totalTime = 0;
        int [] boxes = new int [cashboxes];
        ArrayList<Integer> customersTimeArray = customersTimeToArray(customers);
        System.out.println(customersTimeArray);


        if (cashboxes == 1) {
            for (int time:customersTimeArray) {
                totalTime += time;
            }
            return totalTime;
        }


        if (cashboxes > customers) {
            int currTime = 0;
            for (int time:customersTimeArray) {
                if (time >= currTime)
                    currTime = time;
            }
            return currTime;
        }


        if (cashboxes > 1 && customers > cashboxes) {
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
        }
        return Arrays.stream(boxes).max().getAsInt();
    };


    static ArrayList<Integer> customersTimeToArray(int quantity){
        ArrayList<Integer> customersTime = new ArrayList<>();
        for (int i = 0; i < quantity; i++){
            customersTime.add(new Customer().getTimeAtTheCheckout());
        }
        return customersTime;
    }
}
