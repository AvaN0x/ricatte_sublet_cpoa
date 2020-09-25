package views;

import dao.*;
import models.Category;

public class TestView {
    public static void main(String[] args) {
        try {

            // On initialise notre DAO (notre mode de savegarde) avec MySQL
            DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);

            // On sauvegarde une nouvelle catégorie
            daos.getCategoryDAO().create(new Category(0, "Ma Categorie", "categ.png"));

            // On change notre DAO (notre mode de sauvegarde) avec une liste en mémoire
            daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);

            // On sauvegarde une nouvelle catégorie
            daos.getCategoryDAO().create(new Category(0, "Ma Categorie", "categ.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
