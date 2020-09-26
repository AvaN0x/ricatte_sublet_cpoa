package views;

import java.util.ArrayList;
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
        do {
            System.out.println("\n-- Client menu -- " + _persistance + " \n0/ Quitter\n1/ Ajouter\n2/ Liste");
            System.out.print("Choix : ");

            try {
                var submenu = _scan.nextInt();
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
                System.out.println("Exception: " + e);
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
            _scan.nextLine();
            System.out.print("Voie adresse : ");
            String adrVoie = _scan.nextLine().trim();
            System.out.print("Code postal : ");
            int adrCodePostal = _scan.nextInt();
            _scan.nextLine();
            System.out.print("Ville : ");
            String adrVille = _scan.nextLine().trim();
            System.out.print("Pays : ");
            String adrPays = _scan.nextLine().trim();

            var daos = DAOFactory.getDAOFactory(_persistance);
            daos.getClientDAO().create(new Client(nom, prenom, identifiant, motDePasse, adrNumero, adrVoie,
                    adrCodePostal, adrVille, adrPays));
            System.out.println("Le client a bien été créé");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            _scan.nextLine();
        }
    }

    public static void displayClientMenu() {
        try {
            do {
                var daos = DAOFactory.getDAOFactory(_persistance);
                ArrayList<Client> clients = daos.getClientDAO().getAll();
                System.out.println("\n-- Liste clients -- " + _persistance + " \n0/ Quitter");
                for (int i = 0; i < clients.size(); i++)
                    System.out.println(String.format("%s/ %s", i + 1, clients.get(i)));
                System.out.print("Choix : ");

                try {
                    var submenu = _scan.nextInt();
                    _scan.nextLine();
                    if (submenu == 0)
                        return;
                    else {
                        var cl = clients.get(submenu - 1);
                        clientSelectMenu(cl);
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

    private static void clientSelectMenu(Client cl) {
        System.out.println("\n-- Client -- " + _persistance);
        System.out.println("ID : " + cl.getId());
        System.out.println("Nom : " + cl.getNom());
        System.out.println("Prenom : " + cl.getPrenom());
        System.out.println("Identifiant : " + cl.getIdentifiant());
        System.out.println("Mot de passe : " + cl.getMotDePasse());
        System.out.println("Numéro d'adresse : " + cl.getAdrNumero());
        System.out.println("Voie adresse : " + cl.getAdrVoie());
        System.out.println("Code postal : " + cl.getAdrCodePostal());
        System.out.println("Ville : " + cl.getAdrVille());
        System.out.println("Pays : " + cl.getAdrPays());
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
                        editClientMenu(cl);
                        break;
                    case 2:
                        var daos = DAOFactory.getDAOFactory(_persistance);
                        daos.getClientDAO().delete(cl);
                        System.out.println("Le client a bien été supprimé");
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

    private static void editClientMenu(Client cl) {
        System.out.println("\n-- Modification client numéro " + cl.getId() + "-- " + _persistance
                + "\nLaisser la chaine vide ne modifiera pas la valeur");
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
            String adrNumero = _scan.nextLine().trim();
            System.out.print("Voie adresse : ");
            String adrVoie = _scan.nextLine().trim();
            System.out.print("Code postal : ");
            String adrCodePostal = _scan.nextLine().trim();
            System.out.print("Ville : ");
            String adrVille = _scan.nextLine().trim();
            System.out.print("Pays : ");
            String adrPays = _scan.nextLine().trim();

            if (!nom.isEmpty()) {
                cl.setNom(nom);
                System.out.println("Le nom a été modifié");
            }
            if (!prenom.isEmpty()) {
                cl.setPrenom(prenom);
                System.out.println("Le prenom a été modifié");
            }
            if (!identifiant.isEmpty()) {
                cl.setIdentifiant(identifiant);
                System.out.println("L'identifiant a été modifié");
            }
            if (!motDePasse.isEmpty()) {
                cl.setMotDePasse(motDePasse);
                System.out.println("Le mot de passe a été modifié");
            }
            if (!adrNumero.isEmpty()) {
                cl.setAdrNumero(Integer.parseInt(adrNumero));
                System.out.println("Le numéro d'adresse a été modifié");
            }
            if (!adrVoie.isEmpty()) {
                cl.setAdrVoie(adrVoie);
                System.out.println("La voie a été modifié");
            }
            if (!adrCodePostal.isEmpty()) {
                cl.setAdrCodePostal(Integer.parseInt(adrCodePostal));
                System.out.println("Le code postal a été modifié");
            }
            if (!adrVille.isEmpty()) {
                cl.setAdrVille(adrVille);
                System.out.println("La ville a été modifié");
            }
            if (!adrPays.isEmpty()) {
                cl.setAdrPays(adrPays);
                System.out.println("Le pays a été modifié");
            }

            var daos = DAOFactory.getDAOFactory(_persistance);
            daos.getClientDAO().update(cl);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            _scan.nextLine();
        }

    }

}
