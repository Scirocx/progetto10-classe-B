package it.film.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.security.crypto.bcrypt.BCrypt;


import it.film.entity.Film;

public class FilmDAOimp implements filmDAO {

	private EntityManager em=null;

	public EntityManager getEm() {
		if(em == null) {
			em = EntityManagerHelper.getEntityManager();
		}
		return em;
	}
	
	
	
	/**
	 *  salva un film nel database
	 * 
	 *  @author cataldo aprea 
	 *  @param  f Film da salvare
	 *  @return  
	 */
	public void save(Film f) {
		String incassoCriptato = BCrypt.hashpw(f.getIncasso(),BCrypt.gensalt());
		f.setIncasso(incassoCriptato);
		getEm().getTransaction().begin();
		getEm().persist(f);
		getEm().getTransaction().commit();

	}
	
	/**
	 *  mdifica un film nel database
	 * 
	 *  @author cataldo aprea 
	 *  @param  f Film da salvare
	 *  @return  
	 */
	public void update(Film f) throws Exception {
		Film fi = getEm().find(Film.class, f.getId());
		if(fi==null) {
			 throw new Exception("film non trovato");
		}
		getEm().getTransaction().begin();
		getEm().merge(f);
		getEm().getTransaction().commit();

		
	}

	/**
	 *  elimina un film nel database passandogli l'id del film da eliminare
	 * 
	 *  @author cataldo aprea 
	 *  @param  id primary key del film da eliminare
	 *  @return  
	 */
	public void delete(int id) {
		try {
			getEm().getTransaction().begin();
			em.remove(getfilm(id));
		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.getTransaction().commit();
		}
		
	}
	
	/**
	 *  mostra un determinato film dal database 
	 * 
	 *  @author cataldo aprea 
	 *  @param  id primary key del film da trovare
	 *  @return  Film
	 */
	public Film getfilm(int id) {
		
		return getEm().find(Film.class, id);
	}

	/**
	 *  mostra tutti i film dal database 
	 * 
	 *  @author cataldo aprea 
	 *  @param  
	 *  @return  una lista di Film
	 */
	@SuppressWarnings("unchecked")
	public List<Film> getAllfilm() {
		
		 return (List<Film>) getEm().createNamedQuery("trova_tutti").getResultList();
	}
	
	/**
	 *  mostra tutti i film dal database di un determianto regista
	 * 
	 *  @author cataldo aprea 
	 *  @param  regista parametro per selezionare i film fatto da quel determianto regista
	 *  @return  una lista di Film
	 */
	@SuppressWarnings("unchecked")
	public List<Film> getFilmByRegista(String regista){
		
		return (List<Film>) getEm().createNativeQuery("SELECT * FROM film as f where f.regista = '" + regista +"'").getResultList();
	}



}
