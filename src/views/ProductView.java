package views;

import java.util.Scanner;
import controllers.*;

public class ProductView {
    private static ProductController prodController = new ProductController();

    public static void openProductMenu() {
        System.out.println("\n" + "-- Product menu --" + "\n" + "0/ Back to main menu" + "\n" + "1/ SELECT" + "\n"
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
                    printProducts();
                    break;
                case 2:
                    insertProduct();
                    break;
                case 3:
                    // updateProduct();
                    break;
                case 4:
                    // deleteProduct();
                    break;
                default:
                    openProductMenu();
                    break;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            openProductMenu();
        }
        scan.close();
    }

    public static void printProducts() {
        var Products = prodController.getObjects();
        for (var p : Products) {
            System.out.println(p.getId() + " : " + p.getNom() + " - " + p.getDescription() + " - " + p.getTarif()
                    + " - " + p.getVisuel() + " - " + p.getCategorie().getId());
        }
        openProductMenu();
    }

    public static void insertProduct() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Nom : ");
        String nom = scan.nextLine().trim();
        System.out.print("Description : ");
        String description = scan.nextLine().trim();
        System.out.print("Tarif : ");
        String tarifString = scan.nextLine().trim();
        try {
            float tarif = Float.parseFloat(tarifString);

            System.out.print("Visuel : ");
            String visuel = scan.nextLine().trim();
            System.out.print("ID category : ");
            String id_categoryString = scan.nextLine().trim();
            var id_category = Integer.parseInt(id_categoryString);

            // prodController.addProduct(nom, description, tarif, visuel, id_category);
            openProductMenu();
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            insertProduct();
        }
        scan.close();
    }

    public static void updateProduct() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Id : ");
        String idString = scan.nextLine().trim();
        try {
            int id = Integer.parseInt(idString);
            System.out.print("Nom : ");
            String nom = scan.nextLine().trim();
            System.out.print("Description : ");
            String description = scan.nextLine().trim();
            System.out.print("Tarif : ");
            float tarif = Float.parseFloat(scan.nextLine().trim());
            System.out.print("Visuel : ");
            String visuel = scan.nextLine().trim();
            System.out.print("ID category : ");
            int id_category = Integer.parseInt(scan.nextLine().trim());

            // prodController.updateProduct(id, nom, description, tarif, visuel,
            // id_category);
            openProductMenu();
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            updateProduct();
        }

        scan.close();
    }

    public static void deleteProduct() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Id : ");
        String idString = scan.nextLine().trim();
        try {
            int id = Integer.parseInt(idString);

            prodController.removeObject(id);
            openProductMenu();
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            deleteProduct();
        }

        scan.close();
    }

}
