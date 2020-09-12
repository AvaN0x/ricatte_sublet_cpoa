package views;

import java.util.Scanner;
import controllers.*;

public class ClientView {
    private static ClientController cliController;

    public static void openClientMenu(ClientController cController) {
        cliController = cController;
        openClientMenu();

    }

    public static void openClientMenu() {
        System.out.println("\n" + "-- Client menu --" + "\n" + "0/ Back to main menu" + "\n" + "1/ SELECT" + "\n"
                + "2/ INSERT" + "\n" + "3/ UPDATE" + "\n" + "4/ DELETE" + "\n");
        System.out.print("Choice : ");

        Scanner scan = new Scanner(System.in);
        String prompted = scan.nextLine();
        try {
            int submenu = Integer.parseInt(prompted.trim());
            switch (submenu) {
                case 0:
                    MainView.openMainMenu();
                    break;
                case 1:
                    printClients();
                    break;
                case 2:
                    insertClient();
                    break;
                case 3:
                    updateClient();
                    break;
                case 4:
                    deleteClient();
                    break;
                default:
                    openClientMenu();
                    break;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            openClientMenu();
        }
        scan.close();
    }

    public static void printClients() {
        var Clients = cliController.getObjects();
        for (var c : Clients) {
            System.out.println(c.getId() + " : " + c.getNom() + " - " + c.getPrenom());

        }
        openClientMenu();
    }

    public static void insertClient() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Name : ");
        String nom = scan.nextLine().trim();
        System.out.print("Firstname : ");
        String prenom = scan.nextLine().trim();
        cliController.addObject(nom, prenom);
        openClientMenu();
        scan.close();
    }

    public static void updateClient() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Id : ");
        String idString = scan.nextLine().trim();
        try {
            int id = Integer.parseInt(idString);
            System.out.print("Name : ");
            String nom = scan.nextLine().trim();
            System.out.print("Firstname : ");
            String prenom = scan.nextLine().trim();

            cliController.editObject(id, nom, prenom);
            openClientMenu();
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            updateClient();
        }

        scan.close();
    }

    public static void deleteClient() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Id : ");
        String idString = scan.nextLine().trim();
        try {
            int id = Integer.parseInt(idString);

            cliController.removeObject(id);
            openClientMenu();
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            deleteClient();
        }

        scan.close();
    }

}
