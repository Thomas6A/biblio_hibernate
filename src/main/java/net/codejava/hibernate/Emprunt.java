package net.codejava.hibernate;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emprunt")
public class Emprunt {
	
	private int id;
	private Livre id_livre;
	private String nom;
	private Date date_emprunt;
	private Date date_rendu;
	
	

	
	@Column(name="nom")
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Column(name="date_emprunt")
	public Date getDate_emprunt() {
		return date_emprunt;
	}
	public void setDate_emprunt(Date date_emprunt) {
		this.date_emprunt = date_emprunt;
	}
	
	@Column(name ="date_rendu")
	public Date getDate_rendu() {
		return date_rendu;
	}
	public void setDate_rendu(Date date_rendu) {
		this.date_rendu = date_rendu;
	}
	
	@Id
	@Column(name = "id_emprunt")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="livre_id")
	public Livre getId_livre() {
		return id_livre;
	}
	public void setId_livre(Livre id_livre) {
		this.id_livre = id_livre;
	}
	
	
	
}