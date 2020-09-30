package views;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.DAOFactory;
import dao.Persistance;
import models.Product;
import models.Category;

public class ProductView {
    private static Persistance _persistance;
    private static Scanner _scan;

    public static void openProductMenu(Persistance persistance, Scanner scan) {
        _persistance = persistance;
        _scan = scan;
        var inMenu = true;
        do {
            System.out.println("\n-- Produit menu -- " + _persistance + " \n0/ Quitter\n1/ Ajouter\n2/ Liste");
            System.out.print("Choix : ");

            try {
                var submenu = _scan.nextInt();
                _scan.nextLine();
                switch (submenu) {
                    case 0:
                        inMenu = false;
                        break;
                    case 1:
                        createProductMenu();
                        break;
                    case 2:
                        displayProductMenu();
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Exception: " + e);
                _scan.nextLine();
            }
        } while (inMenu);
    }

    public static void createProductMenu() {
        System.out.println("\n-- Ajouter un produit -- " + _persistance);

        try {
            var daos = DAOFactory.getDAOFactory(_persistance);
            if (daos.getCategoryDAO().getAll().size() > 0) {
                System.out.print("Nom : ");
                String nom = _scan.nextLine().trim();
                System.out.print("Description : ");
                String description = _scan.nextLine().trim();
                System.out.print("Tarif : ");
                Float tarif = _scan.nextFloat();
                _scan.nextLine();
                System.out.print("Visuel : ");
                String visuel = _scan.nextLine().trim();
                Category category = SelectCategory();

                daos.getProductDAO().create(new Product(nom, description, tarif, visuel, category));
                System.out.println("Le produit a bien été crée");
            } else
                System.out.println("Vous ne pouvez pas créer de produit s'il n'existe pas de catégorie");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            _scan.nextLine();
        }
    }

    public static void displayProductMenu() {
        try {

            do {
                var daos = DAOFactory.getDAOFactory(_persistance);
                ArrayList<Product> products = daos.getProductDAO().getAll();
                System.out.println("\n-- Liste produits -- " + _persistance + " \n0/ Quitter");
                for (int i = 0; i < products.size(); i++)
                    System.out.println(String.format("%s/ %s", i + 1, products.get(i).getNom()));
                System.out.print("Choix : ");

                try {
                    var submenu = _scan.nextInt();
                    _scan.nextLine();
                    if (submenu == 0)
                        return;
                    else {
                        var pr = products.get(submenu - 1);
                        productSelectMenu(pr);
                    }

                } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Exception: " + e);
                    _scan.nextLine();
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    private static void productSelectMenu(Product pr) {
        System.out.println("\n-- Product -- " + _persistance);
        System.out.println("ID : " + pr.getId());
        System.out.println("Nom : " + pr.getNom());
        System.out.println("Description : " + pr.getDescription());
        System.out.println("Tarif : " + pr.getTarif());
        System.out.println("Visuel : " + pr.getVisuel());
        System.out.println("Catégorie : " + pr.getCategory().getTitle());

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
                        editProductMenu(pr);
                        break;
                    case 2:
                        var daos = DAOFactory.getDAOFactory(_persistance);
                        daos.getProductDAO().delete(pr);
                        System.out.println("Le produit a bien été supprimé");
                        return;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                _scan.nextLine();
            }
        } while (true);
    }

    private static void editProductMenu(Product pr) {
        System.out.println("\n-- Modification client numéro " + pr.getId() + "-- " + _persistance
                + "\nLaisser la chaine vide ne modifiera pas la valeur");
        try {
            System.out.print("Nom : ");
            String nom = _scan.nextLine().trim();
            System.out.print("Description : ");
            String description = _scan.nextLine().trim();
            System.out.print("Tarif : ");
            String tarif = _scan.nextLine().trim();
            System.out.print("Visuel : ");
            String visuel = _scan.nextLine().trim();
            System.out.print("Modifier la catégorie? (y/n) : ");
            String editCommand = _scan.nextLine().trim().toLowerCase();

            if (editCommand == "y") {
                pr.setCategory(SelectCategory());
                System.out.println("La catégorie a été modifiée");
            }
            if (!nom.isEmpty()) {
                pr.setNom(nom);
                System.out.println("Le nom a été modifié");
            }
            if (!description.isEmpty()) {
                pr.setDescription(description);
                System.out.println("La description a été modifiée");
            }
            if (!tarif.isEmpty()) {
                pr.setTarif(Float.parseFloat(tarif));
                System.out.println("Le tarif a été modifié");
            }
            if (!visuel.isEmpty()) {
                pr.setVisuel(visuel);
                System.out.println("Le visuel a été modifié");
            }

            var daos = DAOFactory.getDAOFactory(_persistance);
            daos.getProductDAO().update(pr);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            _scan.nextLine();
        }
    }

    private static Category SelectCategory() {
        Category category = null;
        try {
            var daos = DAOFactory.getDAOFactory(_persistance);
            ArrayList<Category> categories = daos.getCategoryDAO().getAll();
            for (int i = 0; i < categories.size(); i++)
                System.out.println(String.format("%s/ %s", i, categories.get(i)));
            do {
                System.out.print("Choisissez une catégorie : ");
                try {
                    var submenu = _scan.nextInt();
                    _scan.nextLine();
                    category = categories.get(submenu);
                } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Exception: " + e);
                    _scan.nextLine();
                }
            } while (category == null);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return category;
    }
}
