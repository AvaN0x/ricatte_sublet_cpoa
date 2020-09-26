package views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.DAOFactory;
import dao.Persistance;
import models.Client;
import models.Command;
import models.CommandLine;
import models.Product;

public class CommandView {
    private static Persistance _persistance;
    private static Scanner _scan;

    public static void openCommandMenu(Persistance persistance, Scanner scan) {
        _persistance = persistance;
        _scan = scan;
        var inMenu = true;
        do {
            System.out.println("\n-- Command menu -- " + _persistance + " \n0/ Quitter\n1/ Ajouter\n2/ Liste");
            System.out.print("Choix : ");

            try {
                var submenu = _scan.nextInt();
                _scan.nextLine();
                switch (submenu) {
                    case 0:
                        inMenu = false;
                        break;
                    case 1:
                        createCommandMenu();
                        break;
                    case 2:
                        displayCommandMenu();
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Exception: " + e);
                e.printStackTrace();
                _scan.nextLine();
            }
        } while (inMenu);
    }

    public static void createCommandMenu() {
        System.out.println("\n-- Ajouter une commande -- " + _persistance);

        try {
            System.out.print("Date (dd/MM/yyyy) \n(Laisser vide pour aujourd'hui) : ");
            LocalDate dateCommand = LocalDate.now();
            String sDateCommand = _scan.nextLine().trim();
            if (!sDateCommand.isEmpty())
                dateCommand = LocalDate.parse(sDateCommand, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Client client = SelectClient();

            var daos = DAOFactory.getDAOFactory(_persistance);
            daos.getCommandDAO().create(new Command(dateCommand, client));
            System.out.println("La commande a bien été créé");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            _scan.nextLine();
        }
    }

    public static void displayCommandMenu() {
        try {
            do {
                System.out.println("\n-- Liste commandes -- " + _persistance + " \n0/ Quitter");
                var daos = DAOFactory.getDAOFactory(_persistance);
                ArrayList<Command> cmds = daos.getCommandDAO().getAll();
                for (int i = 0; i < cmds.size(); i++)
                    System.out.println(String.format("%s/ %s", i + 1, cmds.get(i)));
                System.out.print("Choix : ");

                try {
                    var submenu = _scan.nextInt();
                    _scan.nextLine();
                    if (submenu == 0)
                        return;
                    else {
                        var cmd = cmds.get(submenu - 1);
                        CommandSelectMenu(cmd);
                    }

                } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Exception: " + e);
                    e.printStackTrace();
                    _scan.nextLine();
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }

    private static void CommandSelectMenu(Command cmd) {
        do {
            System.out.println("\n-- Commande -- " + _persistance);
            System.out.println("ID : " + cmd.getId());
            System.out.println("Date commande : " + cmd.getDateCommand());
            System.out.println("Client : " + cmd.getClient());
            System.out.println("Produits commandés : ");
            for (HashMap.Entry<Product, CommandLine> line : cmd.getCommandLines().entrySet())
                System.out
                        .println(String.format("- %s : %s exemplaires", line.getKey(), line.getValue().getQuantite()));
            System.out.println(
                    "\n0/ Quitter\n1/ Modifier la date et/ou le client\n2/ Modifier les produits commandés\n3/ Supprimer\n");
            System.out.print("Choix : ");

            try {
                var submenu = _scan.nextInt();
                _scan.nextLine();
                switch (submenu) {
                    case 0:
                        return;
                    case 1:
                        editCommandMenu(cmd);
                        break;
                    case 2:
                        displayCommandLinesMenu(cmd);
                        break;
                    case 3:
                        var daos = DAOFactory.getDAOFactory(_persistance);
                        daos.getCommandDAO().delete(cmd);
                        System.out.println("La commande a bien été supprimée");
                        return;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                e.printStackTrace();
                _scan.nextLine();
            }
        } while (true);
    }

    private static void editCommandMenu(Command cmd) {
        System.out.println("\n-- Modification date et/ou client Commande " + cmd.getId() + "-- " + _persistance
                + "\nLaisser la chaine vide ne modifiera pas la valeur");
        try {
            System.out.print("Date (dd/MM/yyyy) : ");
            LocalDate dateCommand = LocalDate.now();
            String sDateCommand = _scan.nextLine().trim();

            System.out.print("Modifier le client? (y/n) : ");
            String editClient = _scan.nextLine().trim().toLowerCase();
            if (editClient == "y") {
                cmd.setClient(SelectClient());
                System.out.println("Le client a été modifiée");
            }
            if (!sDateCommand.isEmpty()) {
                dateCommand = LocalDate.parse(sDateCommand, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                cmd.setDateCommand(dateCommand);
                System.out.println("La date de commande a été modifiée");
            }

            var daos = DAOFactory.getDAOFactory(_persistance);
            daos.getCommandDAO().update(cmd);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
            _scan.nextLine();
        }
    }

    private static void displayCommandLinesMenu(Command cmd) {
        try {
            do {
                var daos = DAOFactory.getDAOFactory(_persistance);
                var cmdLines = cmd.getCommandLines();
                System.out.println(
                        "\n-- Liste des produits de la commande -- " + _persistance + " \n0/ Quitter\n1/ Ajouter");
                var products = cmdLines.keySet().toArray(new Product[0]);
                for (int i = 0; i < products.length; i++)
                    System.out.println(String.format("%s/ %s", i + 2, products[i], cmdLines.get(products[i])));
                System.out.print("Choix : ");

                try {
                    var submenu = _scan.nextInt();
                    _scan.nextLine();
                    if (submenu == 0)
                        return;
                    else if (submenu == 1)
                        createCommandLineMenu(cmd);
                    else {
                        submenu = submenu - 2;
                        if (submenu >= 0 && submenu < products.length)
                            selectCommandLineMenu(cmd, products[submenu], cmdLines.get(products[submenu]));
                    }

                } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Exception: " + e);
                    e.printStackTrace();
                    _scan.nextLine();
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
            _scan.nextLine();
        }
    }

    private static void selectCommandLineMenu(Command cmd, Product prod, CommandLine line) {
        System.out.println("\n-- Ligne commande -- " + _persistance);
        System.out.println("Produit : " + prod);
        System.out.println("Quantité : " + line.getQuantite());
        System.out.println("Tarif unitaire : " + line.getTarifUnitaire());
        do {
            System.out.println("\n0/ Quitter\n1/ Modifier\n2/ Supprimer\n");
            System.out.print("Choix : ");

            try {
                var submenu = _scan.nextInt();
                _scan.nextLine();
                switch (submenu) {
                    case 0:
                        return;
                    case 1:
                        editCommandLineMenu(cmd, prod, line);
                        break;
                    case 2:
                        cmd.remCommandLine(prod);
                        var daos = DAOFactory.getDAOFactory(_persistance);
                        daos.getCommandDAO().update(cmd);
                        System.out.println("La commande a bien été modifiée");
                        return;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                e.printStackTrace();
                _scan.nextLine();
            }
        } while (true);
    }

    public static void createCommandLineMenu(Command cmd) {
        System.out.println("\n-- Ajouter une ligne de commande -- " + _persistance);
        try {
            Product prod = SelectProduct();
            System.out.print("Quantité : ");
            int quantite = _scan.nextInt();
            _scan.nextLine();

            cmd.addCommandLine(prod, new CommandLine(cmd, quantite, prod.getTarif()));

            var daos = DAOFactory.getDAOFactory(_persistance);
            daos.getCommandDAO().update(cmd);
            System.out.println("La commande a bien été modifiée");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
            _scan.nextLine();
        }
    }

    public static void editCommandLineMenu(Command cmd, Product prod, CommandLine line) {
        System.out.println("\n-- Modifier une ligne de commande -- " + _persistance);
        try {
            System.out.print("Quantite : ");
            String quantite = _scan.nextLine().trim();

            if (prod.getTarif() != line.getTarifUnitaire()) {
                System.out.print("Tarif du produit de la commande : " + line.getTarifUnitaire());
                System.out.print("Tarif du produit actuellement : " + prod.getTarif());
                System.out.print("Mettre à jour le tarif (y/n) : ");
                String editQuantite = _scan.nextLine().trim().toLowerCase();
                if (editQuantite == "y") {
                    line.setTarifUnitaire(prod.getTarif());
                    System.out.println("La catégorie a été modifiée");
                }
            }

            if (!quantite.isEmpty()) {
                line.setQuantite(Integer.parseInt(quantite));
                System.out.println("La quantité a été modifiée");
            }

            cmd.updateCommandLine(prod, line);

            var daos = DAOFactory.getDAOFactory(_persistance);
            daos.getCommandDAO().update(cmd);
            System.out.println("La commande a bien été modifiée");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
            _scan.nextLine();
        }
    }

    private static Client SelectClient() {
        Client client = null;
        try {
            var daos = DAOFactory.getDAOFactory(_persistance);
            ArrayList<Client> clients = daos.getClientDAO().getAll();
            for (int i = 0; i < clients.size(); i++)
                System.out.println(String.format("%s/ %s", i, clients.get(i)));
            do {
                System.out.print("Choisissez un client : ");
                try {
                    var submenu = _scan.nextInt();
                    _scan.nextLine();
                    client = clients.get(submenu);
                } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Exception: " + e);
                    _scan.nextLine();
                }
            } while (client == null);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return client;
    }

    private static Product SelectProduct() {
        Product client = null;
        try {
            var daos = DAOFactory.getDAOFactory(_persistance);
            ArrayList<Product> products = daos.getProductDAO().getAll();
            for (int i = 0; i < products.size(); i++)
                System.out.println(String.format("%s/ %s", i, products.get(i)));
            do {
                System.out.print("Choisissez un client : ");
                try {
                    var submenu = _scan.nextInt();
                    _scan.nextLine();
                    client = products.get(submenu);
                } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Exception: " + e);
                    _scan.nextLine();
                }
            } while (client == null);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
        return client;
    }

}
