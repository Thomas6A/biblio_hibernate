package net.codejava.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "livre")
public class Livre {
	private String Titre;
	private String Auteur;
	private String Genre;
	private int livre_id;
	private int nb_page;
	private int nb_exemplaires;
	
	@Column (name = "titre")
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	
	@Column(name = "auteur")
	public String getAuteur() {
		return Auteur;
	}
	public void setAuteur(String auteur) {
		Auteur = auteur;
	}
	
	@Column(name = "genre")
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	
	@Column(name = "livre_id")
	@Id
	public int getLivre_id() {
		return livre_id;
	}
	public void setLivre_id(int livre_id) {
		this.livre_id = livre_id;
	}
	
	@Column(name = "nb_page")
	public int getNb_page() {
		return nb_page;
	}
	public void setNb_page(int nb_page) {
		this.nb_page = nb_page;
	}
	
	@Column(name = "nb_exemplaires")
	public int getNb_exemplaires() {
		return nb_exemplaires;
	}
	public void setNb_exemplaires(int nb_exemplaires) {
		this.nb_exemplaires = nb_exemplaires;
	}

}
