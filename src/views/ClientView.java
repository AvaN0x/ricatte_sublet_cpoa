package views;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.DAOFactory;
import dao.Persistance;
import models.Client;

public class ClientView {
    private static Persistance _persistance;
    private static Scanner _scan;

    public static void openClientMenu(Persistance persistance, Scanner scan) {
        _persistance = persistance;
        _scan = scan;
        var inMenu = true;
        // TODO get every clients in a var
        do {

            System.out.println("\n-- Client menu -- " + _persistance + " \n0/ Quitter\n1/ Ajoute\n2/Liste");
            // TODO display every clients
            System.out.print("Choix : ");

            try {
                var submenu = scan.nextInt();
                _scan.nextLine();
                switch (submenu) {
                    case 0:
                        inMenu = false;
                        break;
                    case 1:
                        createClientMenu();
                        break;
                    case 2:
                        displayClientMenu();
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

    public static void createClientMenu() {
        System.out.println("\n-- Ajouter un Client -- " + _persistance);

        try {
            System.out.print("Nom : ");
            String nom = _scan.nextLine().trim();
            System.out.print("Prenom : ");
            String prenom = _scan.nextLine().trim();
            System.out.print("Identifiant : ");
            String identifiant = _scan.nextLine().trim();
            System.out.print("Mot de passe : ");
            String motDePasse = _scan.nextLine().trim();
            System.out.print("Numéro adresse : ");
            int adrNumero = _scan.nextInt();
            System.out.print("Voie adresse : ");
            String adrVoie = _scan.nextLine().trim();
            System.out.print("Code postal : ");
            int adrCodePostal = _scan.nextInt();
            System.out.print("Ville : ");
            String adrVille = _scan.nextLine().trim();
            System.out.print("Pays : ");
            String adrPays = _scan.nextLine().trim();

            var daos = DAOFactory.getDAOFactory(_persistance);
            daos.getClientDAO().create(new Client(-1, nom, prenom, identifiant, motDePasse, adrNumero, adrVoie,
                    adrCodePostal, adrVille, adrPays));

        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Exception: " + e.getMessage());
            _scan.nextLine();
        }
    }

    public static void displayClientMenu() {

    }
}
