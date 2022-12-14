package net.codejava.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testlivre {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Bibliotheque");
		EntityManager entityManager = factory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		Livre livre = new Livre();
		livre.setLivre_id(1);
		livre.setTitre("Killig joke");
		livre.setAuteur("Alan Moore");
		livre.setGenre("Comics");
		livre.setNb_page(37);
		livre.setNb_exemplaires(2);
		
		entityManager.persist(livre);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
		
	}

}
