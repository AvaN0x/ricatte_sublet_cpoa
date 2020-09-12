package views;

import java.util.Scanner;

import controllers.*;

public class MainView {
    private static CategoryController categController;
    private static ProductController prodController;

    public static void main(String[] args) {
        controllers.BaseSql.initConnection();

        categController = new CategoryController();
        prodController = new ProductController();

        openMainMenu();
    }

    public static void openMainMenu() {
        System.out.println(
                "\n" + "-- Main menu --" + "\n" + "0/ Exit" + "\n" + "1/ Category" + "\n" + "2/ Product" + "\n");
        System.out.print("Choice : ");

        Scanner scan = new Scanner(System.in);
        String prompted = scan.nextLine();
        try {
            int submenu = Integer.parseInt(prompted.trim());
            switch (submenu) {
                case 0:
                    break;
                case 1:
                    CategoryView.openCategoryMenu(categController);
                    break;
                case 2:
                    ProductView.openProductMenu(categController, prodController);
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
