package views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.DAOFactory;
import dao.Persistance;
import models.Category;

public class CategoryView {
    private static Persistance _persistance;
    private static Scanner _scan;

    public static void openCategoryMenu(Persistance persistance, Scanner scan) {
        _persistance = persistance;
        _scan = scan;
        var inMenu = true;
        do {
            System.out.println("\n-- Category menu -- " + _persistance + " \n0/ Quitter\n1/ Ajouter\n2/ Liste");
            System.out.print("Choix : ");

            try {
                var submenu = _scan.nextInt();
                _scan.nextLine();
                switch (submenu) {
                    case 0:
                        inMenu = false;
                        break;
                    case 1:
                        createCategoryMenu();
                        break;
                    case 2:
                        displayCategoryMenu();
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Exception: " + e.getMessage());
                _scan.nextLine();
            }
        } while (inMenu);
    }

    public static void createCategoryMenu() {
        System.out.println("\n-- Ajouter une catégorie -- " + _persistance);

        try {
            System.out.print("Titre : ");
            String title = _scan.nextLine().trim();
            System.out.print("Visuel : ");
            String visuel = _scan.nextLine().trim();

            var daos = DAOFactory.getDAOFactory(_persistance);
            daos.getCategoryDAO().create(new Category(-1, title, visuel));

        } catch (SQLException | NumberFormatException | InputMismatchException e) {
            System.out.println("Exception: " + e.getMessage());
            _scan.nextLine();
        }
    }

    public static void displayCategoryMenu() {
        var daos = DAOFactory.getDAOFactory(_persistance);
        ArrayList<Category> categories = daos.getCategoryDAO().getAll();
        System.out.println("\n-- Liste categories -- " + _persistance + " \n0/ Quitter");
        for (int i = 0; i < categories.size(); i++)
            System.out.println(String.format("\n%s/ %s", i + 1, categories.get(i).getTitle()));
        System.out.print("Choix : ");

        do {
            try {
                var submenu = _scan.nextInt();
                _scan.nextLine();
                if (submenu == 0)
                    return;
                else {
                    var ca = categories.get(submenu - 1);
                    categorySelectMenu(ca);
                }

            } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Exception: " + e.getMessage());
                _scan.nextLine();
            }
        } while (true);
    }

    private static void categorySelectMenu(Category ca) {
        System.out.println("\n-- Category -- " + _persistance);
        System.out.println("\nID : " + ca.getId());
        System.out.println("\nTitre : " + ca.getTitle());
        System.out.println("\nVisuel : " + ca.getVisuel());
        do {
            System.out.println("\n0/Quitter\n1/Modifier\n2/Supprimer\n");
            System.out.print("Choix : ");

            try {
                var submenu = _scan.nextInt();
                _scan.nextLine();
                switch (submenu) {
                    case 0:
                        return;
                    case 1:
                        editCategoryMenu(ca);
                        break;
                    case 2:
                        var daos = DAOFactory.getDAOFactory(_persistance);
                        daos.getCategoryDAO().delete(ca);
                        return;
                    default:
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Exception: " + e.getMessage());
                _scan.nextLine();
            }
        } while (true);
    }

    private static void editCategoryMenu(Category ca) {
        System.out.println("\n-- Modification client numéro " + ca.getId() + "-- " + _persistance
                + "\nLaisser la chaine vide ne modifiera pas la valeur");
        try {
            System.out.print("Titre : ");
            String title = _scan.nextLine().trim();
            if (!title.isEmpty())
                ca.setTitle(title);
            System.out.print("Visuel : ");
            String visuel = _scan.nextLine().trim();
            if (!visuel.isEmpty())
                ca.setVisuel(visuel);

            var daos = DAOFactory.getDAOFactory(_persistance);
            daos.getCategoryDAO().update(ca);

        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Exception: " + e.getMessage());
            _scan.nextLine();
        }

    }

}
