package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    private Controller controller;

    private void mainMenu(Scanner sc) {
        String option = "ghyt";
        do {
            switch (option) {
                case "b" -> controller.addBook();
                case "u" -> {
                    System.out.println("Enter current book title: ");
                    String currentBookTitle = sc.nextLine();
                    System.out.println("Enter new title: ");
                    String newTitle = sc.nextLine();
                    System.out.println("Enter new ISBN: ");
                    String newISBN = sc.nextLine();
                    controller.updateBook(currentBookTitle, newTitle, newISBN);
                }
                case "s" -> controller.searchBook(sc);
                default -> {
                    if (!option.equalsIgnoreCase("ghyt")) {
                        System.out.println("Nem ismert opcio...");
                    }
                }
            }

            printMenu();
            System.out.println("Mit lepsz?");
        } while (!"q".equalsIgnoreCase(option = sc.nextLine()));
    }

    private void printMenu() {
        System.out.println("=".repeat(30));
        System.out.println("\tUj Konyv Felvetele - (b)");
        System.out.println("\t Konyv Modositasa - (u)");
        System.out.println("\t Konyv Keresese - (s)");
        System.out.println("\tKilepes - (q)");
        System.out.println("=".repeat(30));
    }

    public static void main(String[] args) throws Exception {
        Main m = new Main();

        try (
                Scanner sc = new Scanner(System.in);
                Controller c = new Controller()
        ) {
            m.controller = c;
            m.mainMenu(sc);
        }
    }
}
