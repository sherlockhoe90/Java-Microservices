package com.example.dsa;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DsaApplication {

    public static void main(String[] args) {
        System.out.println("Try programiz.pro");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a name: ");
        String name = sc.nextLine();

        System.out.println("Enter the number of times you want to print it: ");
        int n = sc.nextInt();

        printNameUsingRecursion(n, name);
    }

    private static void printNameUsingRecursion(int k, String name) {
        if (k < 1) return;
        printNameUsingRecursion(k - 1, name);
        System.out.println(name);
    }
}