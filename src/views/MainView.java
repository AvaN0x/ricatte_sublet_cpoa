package views;

import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
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
                    CategoryView.openCategoryMenu();
                    break;
                case 2:
                    ProductView.openProductMenu();
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
