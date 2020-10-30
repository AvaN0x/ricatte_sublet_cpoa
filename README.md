# iut_cpoa

A repo made for school stuff

Using jdk 15 and github branches

## Notice

La connexion à la base de données nécessite un fichier `creditentials.properties` dans le dossier `config`.

## Fonctionnalités

<!-- ❌✅ -->
| Globalement                              |     |
| :--------------------------------------- | --- |
| Changement de mode MYSQL / Liste Mémoire | ✅   |
| Pas de suppression en cascade            | ✅   |

| Clients               |     |
| :-------------------- | --- |
| Création              | ✅   |
| Affichage             | ✅   |
| Modification          | ✅   |
| Suppression           | ✅   |
| Filtre par nom        | ✅   |
| Filtre par nom prénom | ✅   |

| Catégories   |     |
| :----------- | --- |
| Création     | ✅   |
| Affichage    | ✅   |
| Modification | ✅   |
| Suppression  | ✅   |

| Produits             |     |
| :------------------- | --- |
| Création             | ✅   |
| Affichage            | ✅   |
| Modification         | ✅   |
| Suppression          | ✅   |
| Filtre par catégorie | ✅   |
| Tri par catégorie    | ✅   |
| Filtre par tarif     | ✅   |
| Tri par tarif        | ✅   |

| Commandes                                  |     |
| :----------------------------------------- | --- |
| Création                                   | ✅   |
| Affichage                                  | ✅   |
| Ajout et suppression de produits commandés | ✅   |
| Suppression                                | ✅   |
| Tri par client                             | ✅   |
| Filtre par client                          | ✅   |
| Filtre par produit                         | ✅   |
| Filtre par date                            | ✅   |

## Répartition

Les tâches ont bien étaient répartie et on arrive a un équilibre parfait :

**Clément** : 50%

Il s'est chargé de tout les fichiers `FXML`, d'une partie des contrôleurs (notamment les détails d'un objet, du tri par défaut et les liens entre différents objets) ainsi que de divers réparations de [bugs](https://github.com/AvaN0x/ricatte_sublet_cpoa/issues?q=is%3Aissue).

**Tom** : 50%

Il s'est chargé de toute la structure (division en plusieurs `packages`), du reste des contrôleurs (notamment l'asynchrone, la barre de recherche, du changement de persistance) ainsi que de divers réparations de [bugs](https://github.com/AvaN0x/ricatte_sublet_cpoa/issues?q=is%3Aissue).

Vous pouvez voir le détail de la répartition dans le [projet GitHub](https://github.com/AvaN0x/ricatte_sublet_cpoa/projects/4).
