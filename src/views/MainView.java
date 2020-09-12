package views;

import java.util.Scanner;

import controllers.*;

public class MainView {
    private static CategoryController categController;
    private static ProductController prodController;
    private static ClientController cliController;

    public static void main(String[] args) {
        controllers.BaseSql.initConnection();

        categController = new CategoryController();
        prodController = new ProductController();
        cliController = new ClientController();

        openMainMenu();
    }

    public static void openMainMenu() {
        System.out.println("\n" + "-- Main menu --" + "\n" + "0/ Exit" + "\n" + "1/ Category" + "\n" + "2/ Product"
                + "\n" + "3/ Client" + "\n");
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
                case 3:
                    ClientView.openClientMenu(cliController);
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
