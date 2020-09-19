package views;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.Persistance;

public class ClientView {
    private static Persistance persistance;
    private static Scanner scan;

    public static void openClientMenu(Persistance _persistance, Scanner _scan) {
        persistance = _persistance;
        scan = _scan;
        var inMenu = true;
        // TODO get every clients in a var
        do {

            System.out.println("\n-- Client menu -- " + persistance + " \n0/ Quitter\n1/ Ajouter");
            // TODO display every clients
            System.out.print("Choix : ");

            try {
                var submenu = scan.nextInt();
                scan.nextLine();
                switch (submenu) {
                    case 0:
                        inMenu = false;
                        break;
                    case 1:
                        createClientMenu();
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Exception: " + e.getMessage());
                scan.nextLine();
            }
        } while (inMenu);
    }

    public static void createClientMenu() {
        System.out.println("\n-- Ajouter un Client -- " + persistance);

        try {
            System.out.print("Nom : ");
            String nom = scan.nextLine().trim();
            System.out.print("Prenom : ");
            String prenom = scan.nextLine().trim();
            System.out.print("Identifiant : ");
            String identifiant = scan.nextLine().trim();
            System.out.print("Mot de passe : ");
            String motDePasse = scan.nextLine().trim();
            System.out.print("Numéro adresse : ");
            int adrNumero = scan.nextInt();
            System.out.print("Voie adresse : ");
            String adrVoie = scan.nextLine().trim();
            System.out.print("Code postal : ");
            int adrCodePostal = scan.nextInt();
            System.out.print("Ville : ");
            String adrVille = scan.nextLine().trim();
            System.out.print("Pays : ");
            String adrPays = scan.nextLine().trim();

            // TODO create the client
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Exception: " + e.getMessage());
            scan.nextLine();
        }
    }
}
