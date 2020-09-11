package views;

import java.util.Scanner;
import controllers.*;

public class CategoryView {
    private static CategoryController categController;
    public static void main(String[] args) {
        categController = new CategoryController();
        openCategoryMenu();
    }

    public static void openCategoryMenu() {
        System.out.println("\n" + 
            "-- Category menu --" + "\n" + 
            "1/ SELECT" + "\n" + 
            "2/ INSERT" + "\n" + 
            "3/ UPDATE" + "\n" + 
            "4/ DELETE" + "\n" 
        );
        System.out.print("Choice : ");

        Scanner scan = new Scanner(System.in);
        String prompted = scan.nextLine();
	    try {
            int submenu = Integer.parseInt(prompted.trim());
            switch (submenu) {
                case 1 : 
                    printCategories();
                    break;
                case 2 : 
                    insertCategory();
                    break;
                case 3 : 
                    updateCategory();
                    break;
                case 4 : 
                    deleteCategory();
                    break;
                default :
                    openCategoryMenu();
                    break;
            }
        }
        catch (NumberFormatException nfe) {	    	
            System.out.println("NumberFormatException: " + nfe.getMessage());
            openCategoryMenu();
        }
        scan.close();
    }

    public static void printCategories() {
        var categories = categController.getCategories();
        for (var c : categories) {
            System.out.println(c.getId() + " : " + c.getTitle() + " - " + c.getVisuel());
        }
        openCategoryMenu();
    }

    public static void insertCategory() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Title : ");
        String title = scan.nextLine().trim();
        System.out.print("Visuel : ");
        String visuel = scan.nextLine().trim();

        categController.addCategory(title, visuel);
        scan.close();
    }

    public static void updateCategory() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Id : ");
        String idString = scan.nextLine();
        try {
            var id = Integer.parseInt(idString.trim());
            
            System.out.print("New title : ");
            String newTitle = scan.nextLine().trim();
            System.out.print("New visuel : ");
            String newVisuel = scan.nextLine().trim();

            categController.editCategory(id, newTitle, newVisuel);
            openCategoryMenu();
        }
        catch (NumberFormatException nfe) {	    	
            System.out.println("NumberFormatException: " + nfe.getMessage());
            updateCategory();
        }

        scan.close();
    }

    public static void deleteCategory() {
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.print("Id : ");
        String idString = scan.nextLine();
        try {
            var id = Integer.parseInt(idString.trim());
            
            categController.removeCategory(id);
            openCategoryMenu();
        }
        catch (NumberFormatException nfe) {	    	
            System.out.println("NumberFormatException: " + nfe.getMessage());
            updateCategory();
        }

        scan.close();
    }

}
