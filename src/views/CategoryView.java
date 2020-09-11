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
}
