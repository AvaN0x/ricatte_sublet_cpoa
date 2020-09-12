package views;

import java.lang.reflect.InvocationTargetException;
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
        var Clients = cliController.getDistantObjects();
        for (var c : Clients) {
            System.out.println(c.getId() + " : " + c.getNom() + " - " + c.getPrenom());

        }
        openClientMenu();
    }

    public static void insertClient() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Nom : ");
        String nom = scan.nextLine().trim();
        System.out.print("Prenom : ");
        String prenom = scan.nextLine().trim();
        try {
            cliController.addObject(nom, prenom);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | SecurityException e) {
            System.out.println("Error when adding the object: " + e.getMessage());
        }
        openClientMenu();
        scan.close();
    }

    public static void updateClient() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("ID : ");
        String idString = scan.nextLine().trim();
        try {
            int id = Integer.parseInt(idString);
            System.out.print("Nom : ");
            String nom = scan.nextLine().trim();
            System.out.print("Prenom : ");
            String prenom = scan.nextLine().trim();

            try {
                cliController.editObject(id, nom, prenom);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                    | SecurityException e) {
                System.out.println("Error when editing the object: " + e.getMessage());
            }
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
        System.out.print("ID : ");
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