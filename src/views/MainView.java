package views;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.Persistance;

public class MainView {
    private static Persistance _persistance = Persistance.MYSQL;
    private static Scanner _scan;

    public static void openMainMenu() {
        var inMenu = true;
        _scan = new Scanner(System.in);
        do {

            System.out.println("\n-- Main menu -- " + _persistance
                    + " \n0/ Quitter\n1/ Settings\n2/ Client\n3/ Catégorie\n4/ Produit\n5/ Commandes\n");
            System.out.print("Choix : ");

            try {
                var submenu = _scan.nextInt();
                _scan.nextLine();
                switch (submenu) {
                    case 0:
                        inMenu = false;
                        break;
                    case 1:
                        openSettingsMenu();
                        break;
                    case 2:
                        ClientView.openClientMenu(_persistance, _scan);
                        break;
                    case 3:
                        CategoryView.openCategoryMenu(_persistance, _scan);
                        break;
                    case 4:
                        ProductView.openProductMenu(_persistance, _scan);
                        break;
                    case 5:
                        CommandView.openCommandMenu(_persistance, _scan);
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Exception: " + e);
                _scan.nextLine();
            }
        } while (inMenu);
        _scan.close();
    }

    public static void openSettingsMenu() {
        var inMenu = true;
        do {

            System.out.println("\n-- Settings menu --\n0/ Quitter\n1/ Liste Mémoire\n2/ MySQL\n");
            System.out.print("Choix : ");

            try {
                var submenu = _scan.nextInt();
                _scan.nextLine();
                switch (submenu) {
                    case 0:
                        inMenu = false;
                        break;
                    case 1:
                        _persistance = Persistance.LISTE_MEMOIRE;
                        inMenu = false;
                        break;
                    case 2:
                        _persistance = Persistance.MYSQL;
                        inMenu = false;
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Exception: " + e);
                _scan.nextLine();
            }
        } while (inMenu);
    }

}