package views;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import controllers.*;

public class CategoryView {
    private static CategoryController categController;

    public static void openCategoryMenu(CategoryController cController) {
        categController = cController;
        openCategoryMenu();
    }

    public static void openCategoryMenu() {
        System.out.println("\n" + "-- Category menu --" + "\n" + "0/ Back to main menu" + "\n" + "1/ SELECT" + "\n"
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
                    printCategories();
                    break;
                case 2:
                    insertCategory();
                    break;
                case 3:
                    updateCategory();
                    break;
                case 4:
                    deleteCategory();
                    break;
                default:
                    openCategoryMenu();
                    break;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            openCategoryMenu();
        }
        scan.close();
    }

    public static void printCategories() {
        var categories = categController.getDistantObjects();
        for (var c : categories) {
            System.out.println(c.getId() + " : " + c.getTitle() + " - " + c.getVisuel());
        }
        openCategoryMenu();
    }

    public static void insertCategory() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Titre : ");
        String title = scan.nextLine().trim();
        System.out.print("Visuel : ");
        String visuel = scan.nextLine().trim();

        try {
            categController.addObject(title, visuel);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | SecurityException e) {
            System.out.println("Error when adding the object: " + e.getMessage());
        }
        openCategoryMenu();
        scan.close();
    }

    public static void updateCategory() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("ID : ");
        String idString = scan.nextLine();
        try {
            var id = Integer.parseInt(idString.trim());

            System.out.print("Titre : ");
            String newTitle = scan.nextLine().trim();
            System.out.print("Visuel : ");
            String newVisuel = scan.nextLine().trim();

            try {
                categController.editObject(id, newTitle, newVisuel);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                    | SecurityException e) {
                System.out.println("Error when editing the object: " + e.getMessage());
            }
            openCategoryMenu();
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            updateCategory();
        }

        scan.close();
    }

    public static void deleteCategory() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("ID : ");
        String idString = scan.nextLine();
        try {
            var id = Integer.parseInt(idString.trim());

            categController.removeObject(id);
            openCategoryMenu();
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            deleteCategory();
        }

        scan.close();
    }

}
