package views;

import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        openMainMenu();
    }

    public static void openMainMenu() {
        System.out.println("\n" + "-- Main menu --" + "\n" + "0/ Quitter" + "\n" + "1/ Category" + "\n" + "2/ Product"
                + "\n" + "3/ Client" + "\n");
        System.out.print("Choice : ");

        Scanner scan = new Scanner(System.in);
        String prompted = scan.nextLine();
        try {
            int submenu = Integer.parseInt(prompted.trim());
            switch (submenu) {
                case 0:
                    break;
                default:
                    openMainMenu();
                    break;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            openMainMenu();
        }
        scan.close();

    }
}