package net.codejava.hibernate;

import java.util.Scanner;

public class Brief_biblio_2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Choisissez votre utilisation:");
		System.out.println("1 - Saisir un nouveau livre");
		System.out.println("2 - Modifier un livre");
		System.out.println("3 - Afficher la liste des livres");
		System.out.println("4 - Rechercher un livre et afficher son détail");
		System.out.println("5 - Empruntez un livre");
		System.out.println("6 - Rendre un livre");
		System.out.println("7 - Fermer programme");
		System.out.println("Saisissez votre choix:");
		int choix = input.nextInt();
		switch(choix) {
		case 1:
			Methode_livre.Creer_livre();
			main(null);
		case 2:
			Methode_livre.Modifier_livre();
			main(null);
		case 3:
			Methode_livre.Liste_livre();
			main(null);
		case 4:
			Methode_livre.Detail_livre();
			main(null);
		case 5:
			Methode_livre.Emprunt_livre();
			main(null);
		case 6:
			Methode_livre.Rendu_livre();
			main(null);
		case 7:
			break;
		default:
			System.out.println("Choisissez l'un des choix ci-dessous");
			main(null);
		}
		input.close();
		
	}

}
