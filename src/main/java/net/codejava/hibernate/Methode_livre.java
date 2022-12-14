package net.codejava.hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Methode_livre {
	
	/*
	 * Méthode permettant de créer  un livre en saisissant son titre, son auteur, son genre, son nombre de page et son nombre d'exemplaire
	 */
	public static void Creer_livre() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Bibliotheque");
		EntityManager entityManager = factory.createEntityManager();

		entityManager.getTransaction().begin();

		try {
			System.out.println("Entrez le titre du livre");
			String titre = input.nextLine();
			System.out.println("Entrez l'auteur du livre");
			String auteur = input.nextLine();
			System.out.println("Entrez le genre du livre");
			String genre = input.nextLine();
			System.out.println("Entrez le nombre de page du livre");
			int nb_page = input.nextInt();
			System.out.println("Entrez le nombre d'exemplaire du livre");
			int nb_exemplaire = input.nextInt();

			Livre livre = new Livre();
			livre.setTitre(titre);
			livre.setAuteur(auteur);
			livre.setGenre(genre);
			livre.setNb_page(nb_page);
			livre.setNb_exemplaires(nb_exemplaire);

			entityManager.persist(livre);
		} catch (InputMismatchException e) {
			System.out.println("Veuillez rentrez des données correctes");
			Creer_livre();
		}

		entityManager.getTransaction().commit();

		entityManager.close();
		factory.close();
	}
	
	/*
	 * 	Méthode permettant de modifier un livre en saisissant son titre
	 * Pour modifier une valeur il faut saisir le bon choix en chiffre
	 */

	public static void Modifier_livre() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Entrez le titre du livre que vous voulez modifier");
		String titre = input.nextLine();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Bibliotheque");
		EntityManager entityManager = factory.createEntityManager();

		entityManager.getTransaction().begin();
		try {
			Query query = entityManager.createQuery("SELECT u from Livre u where u.titre = :empTitre");
			query.setParameter("empTitre", titre);
			Livre livre = (Livre) query.getSingleResult();
			int choix = 0;

			while (choix != 6) {
				try {
					System.out.println("Choisissez Qu'est-ce que vous voulez modifier:");
					System.out.println("1 - Le titre du livre");
					System.out.println("2 - L'auteur du livre");
					System.out.println("3 - Le genre du livre");
					System.out.println("4 - Le nombre de page du livre");
					System.out.println("5 - Le nombre de livre");
					System.out.println("6 - Arreter la modification");
					System.out.println("Saisissez votre choix:");
					choix = input.nextInt();
					input.nextLine();
					switch (choix) {
					case 1:
						System.out.println("Entrez le nouveau titre");
						String Ntitre = input.nextLine();
						livre.setTitre(Ntitre);
						break;
					case 2:
						System.out.println("Entrez le nouvel auteur");
						String auteur = input.nextLine();
						livre.setAuteur(auteur);
						break;
					case 3:
						System.out.println("Entrez le nouveau genre");
						String genre = input.nextLine();
						livre.setGenre(genre);
						break;
					case 4:
						System.out.println("Entrez le nouveau nombre de page");
						int nb_page = input.nextInt();
						livre.setNb_page(nb_page);
						break;
					case 5:
						System.out.println("Entrez le nouveau nombre d'exemplaire");
						int nb_exemplaire = input.nextInt();
						livre.setNb_exemplaires(nb_exemplaire);
						break;
					case 6:
						break;
					default:
						System.out.println("Entrez un des choix suivants");
					}
				} catch (InputMismatchException e) {
					System.out.println("Veuillez rentrer un chffre :");
					input.nextLine();
				}
			}
			entityManager.merge(livre);
		} catch (NoResultException e) {
			System.out.println("Le titre renté n'est pas bon");
			Modifier_livre();
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	/*
	 * Méthode permettant de lister les livres contenu dans la base de donnée
	 */

	@SuppressWarnings("unchecked")
	public static void Liste_livre() {

		ArrayList<Livre> livres = new ArrayList<Livre>();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Bibliotheque");
		EntityManager entityManager = factory.createEntityManager();

		Query query = entityManager.createQuery("select u from Livre u");
		livres = (ArrayList<Livre>) query.getResultList();

		for (Livre livre : livres) {
			int id = livre.getLivre_id();
			String titre = livre.getTitre();
			System.out.println("id : " + id + " Titre : " + titre);
		}

		entityManager.close();
		factory.close();
	}
	
	/*
	 * Méthode permettant d'avoir le détail d'un livre en saisissant son titre
	 */

	public static void Detail_livre() {

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Entrez le titre du livre dont vous voulez le détail");
		String titre = input.nextLine();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Bibliotheque");
		EntityManager entityManager = factory.createEntityManager();

		try {
			Query query = entityManager.createQuery("select u from Livre u where u.titre = :titre");
			query.setParameter("titre", titre);
			Livre livre = (Livre) query.getSingleResult();

			int id = livre.getLivre_id();
			String auteur = livre.getAuteur();
			String genre = livre.getGenre();
			int nb_page = livre.getNb_page();
			int nb_exemplaire = livre.getNb_exemplaires();
			System.out.println("id : " + id + " Titre : " + titre + " Auteur : " + auteur + " Genre : " + genre
					+ " Nombre de page : " + nb_page + " Nombre d'exemplaire disponible : " + nb_exemplaire);
		} catch (NoResultException e) {
			System.out.println("Ce livre n'existe pas");
			Detail_livre();
		}

		entityManager.close();
		factory.close();
	}
	
	/*
	 * Méthode permettant d'emprunter un livre en saisissant son titre puis en saisissant un nom pour la réservation
	 */

	public static void Emprunt_livre() {

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Entrez le titre du livre que vous voulez emprunter");
		String titre = input.nextLine();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Bibliotheque");
		EntityManager entityManager = factory.createEntityManager();

		try {
			Query query = entityManager.createQuery("select u from Livre u where u.titre = :titre");
			query.setParameter("titre", titre);

			entityManager.getTransaction().begin();

			Livre livre = (Livre) query.getSingleResult();
			if (livre.getNb_exemplaires() != 0) {
				Emprunt emprunt = new Emprunt();
				emprunt.setId_livre(livre.getLivre_id());
				System.out.println("Entrez le nom de la personne qui emprunte");
				String nom = input.nextLine();
				emprunt.setNom(nom);
				LocalDate date_emprunt = LocalDate.now();
				emprunt.setDate_emprunt(java.sql.Date.valueOf(date_emprunt));
				LocalDate date_rendu = date_emprunt.plusMonths(1);
				emprunt.setDate_rendu(java.sql.Date.valueOf(date_rendu));

				entityManager.persist(emprunt);

				livre.setNb_exemplaires(livre.getNb_exemplaires() - 1);

				entityManager.merge(livre);
			} else {
				System.out.println("Ce livre n'a plus d'exemplaire disponible");
			}
		} catch (NoResultException e) {
			System.out.println("Ce livre n'existe pas");
			Emprunt_livre();
		}

		entityManager.getTransaction().commit();

		entityManager.close();
		factory.close();
	}
	
	/*
	 * Méthode permettant de rendre un livre emprunté en saisissant son titre et le nom de la réservation. Puis comparer la date d'aujourd'hui avec la date pour de rendu.
	 */

	public static void Rendu_livre() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Entrez le titre du livre que vous voulez rendre");
		String titre = input.nextLine();
		System.out.println("Entrez le nom de l'emprunt");
		String nom = input.nextLine();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Bibliotheque");
		EntityManager entityManager = factory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		try {
			Query query1 = entityManager.createQuery("select l from Livre l where titre = :titre");
			query1.setParameter("titre", titre);

			Livre livre = (Livre) query1.getSingleResult();

			Query query2 = entityManager.createQuery("select e from Emprunt e where id_livre = :livre and nom =:nom");
			query2.setParameter("livre", livre.getLivre_id());
			query2.setParameter("nom", nom);

			Emprunt emprunt = (Emprunt) query2.getSingleResult();
			LocalDate date_maintenant = LocalDate.now();
			if (java.sql.Date.valueOf(date_maintenant).after(emprunt.getDate_rendu())) {
				System.out.println("Vous le rendez en retard donc des frais seront ajoutés");
			}
			entityManager.remove(emprunt);

			livre.setNb_exemplaires(livre.getNb_exemplaires() + 1);

			entityManager.merge(livre);
		} catch (NoResultException e) {
			System.out.println("Il y a eu une erreur durant le rendu veuillez reprendre la saisie");
			Rendu_livre();
		}
		entityManager.getTransaction().commit();

		entityManager.close();
		factory.close();

	}

}
