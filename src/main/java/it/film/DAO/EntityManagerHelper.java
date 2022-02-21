package it.film.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {
	private static EntityManagerFactory emf= null;
	
	public static EntityManagerFactory getEmf() {
		if(emf== null) {
			emf = Persistence.createEntityManagerFactory("filmps");
		}
		return emf;
	}

	public static void setEmf(EntityManagerFactory emf) {
		EntityManagerHelper.emf = emf;
	}

	public static EntityManager getEntityManager() {
		return getEmf().createEntityManager();}

}
