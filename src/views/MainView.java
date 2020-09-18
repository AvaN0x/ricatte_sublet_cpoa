package views;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.Persistance;

public class MainView {
    private static Persistance persistance;
    private static Scanner scan;

    public static void main(String[] args) {
        persistance = Persistance.MYSQL;
        openMainMenu();
    }

    public static void openMainMenu() {
        var inMenu = true;
        scan = new Scanner(System.in);
        do {

            System.out.println("\n-- Main menu -- " + persistance
                    + " \n0/ Quitter\n1/ Settings\n2/ Category\n3/ Product\n4/ Client\n");
            System.out.print("Choix : ");

            try {
                var submenu = scan.nextInt();
                scan.nextLine();
                switch (submenu) {
                    case 0:
                        inMenu = false;
                        break;
                    case 1:
                        openSettingsMenu();
                        break;
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

    public static void openSettingsMenu() {
        var inMenu = true;
        do {

            System.out.println("\n-- Settings menu --\n0/ Quitter\n1/ Liste Mémoire\n2/ MySQL\n");
            System.out.print("Choix : ");

            try {
                var submenu = scan.nextInt();
                scan.nextLine();
                switch (submenu) {
                    case 0:
                        inMenu = false;
                        break;
                    case 1:
                        persistance = Persistance.LISTE_MEMOIRE;
                        inMenu = false;
                        break;
                    case 2:
                        persistance = Persistance.MYSQL;
                        inMenu = false;
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Exception: " + e.getMessage());
                scan.nextLine();
            }
        } while (inMenu);
    }

}