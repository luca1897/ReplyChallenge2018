package com.pokik.challenge;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> filenames = new ArrayList<>();
        filenames.add("C:\\Users\\User\\Downloads\\first_adventure");
        filenames.add("C:\\Users\\User\\Downloads\\second_adventure");
        filenames.add("C:\\Users\\User\\Downloads\\third_adventure");
        filenames.add("C:\\Users\\User\\Downloads\\fourth_adventure");


        for (String filename: filenames) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Solver solver = new Solver(filename);
                    solver.solve();
                }
            }).start();
        }
    }
}
