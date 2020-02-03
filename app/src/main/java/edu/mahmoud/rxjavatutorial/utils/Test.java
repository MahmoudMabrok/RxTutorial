package edu.mahmoud.rxjavatutorial.utils;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner("Baby names are easiest to understand when they're organized in lists: by gender, by style, by popularity. Nameberry includes hundreds of baby name lists of");

        while (sc.hasNext()) {
            String n = sc.next();
            System.out.print("\"+n+\",");
        }
    }
}
