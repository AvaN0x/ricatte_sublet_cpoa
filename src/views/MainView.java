package views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    private static boolean inMenu;

    public static void main(String[] args) {
        openMainMenu();
    }

    public static void openMainMenu() {
        inMenu = true;
        Scanner scan = new Scanner(System.in);
        do {

            System.out.println("\n-- Main menu --\n0/ Quitter\n1/ Category\n2/ Product\n3/ Client\n");
            System.out.print("Choice : ");

            try {
                var submenu = scan.nextInt();
                scan.nextLine();
                switch (submenu) {
                    case 0:
                        inMenu = false;
                    default:
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Exception: " + e.getMessage());
                scan.nextLine();
            }
        } while (inMenu);
        scan.close();

    }
}