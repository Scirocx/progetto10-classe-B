package it.film.DAO;

import java.util.List;

import it.film.entity.Film;

public interface filmDAO{
	
	public void save(Film f);
	public void update(Film f) throws Exception;
	public void delete(int id);
	public Film getfilm (int id);
	public List<Film> getAllfilm();
	public List<Film> getFilmByRegista(String regista);


}
