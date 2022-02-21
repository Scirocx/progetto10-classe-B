package it.film.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.film.DAO.FilmDAOimp;
import it.film.DAO.filmDAO;
import it.film.entity.Film;

@RestController
@RequestMapping("/film")
@Api(value = "FilmRest", tags = "gestione dei film")
public class FilmRest {
	filmDAO fd = new FilmDAOimp();
	
	@PostMapping
	@ApiOperation(value = "inserimento di un film", notes = "permettere di inserire un film")
	public ResponseEntity<String> inserisci(@RequestBody Film c){
		try {
			fd.save(c);
			return new ResponseEntity<String> ("film salvato con successo", HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<String> ("film non salvato", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	@ApiOperation(value = "vedi tutti i film", notes = "permette di vedere tutti i film inseriti nel db")
	public ResponseEntity<List<Film>> getAllFilm(){
		try {
			return new ResponseEntity<List<Film>>(fd.getAllfilm(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Film>>((List<Film>) null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "aggiorna un film", notes = "permette di aggiortnare un film")
	public ResponseEntity<String> update(@RequestBody Film f, @PathVariable int id){
		f.setId(id);
		try {
			fd.update(f);
			return new ResponseEntity<String>("film aggiornato", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("film non aggiornato", HttpStatus.BAD_REQUEST);
			// TODO: handle exception
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "cancella un film", notes = "permette di cancellare un film")
	public ResponseEntity<String> delete(int d){
		try {
			fd.delete(d);
			return new ResponseEntity<String>("film eliminato con successo", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("impossiible eliminare il film! id inserito non trovato", HttpStatus.OK);
			// TODO: handle exception
		}
		
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "trova film", notes = "permetter di trovare un film tramite un id")
	public ResponseEntity<Film> getCittadinoById(int id){
		try {
			return new ResponseEntity<Film>(fd.getfilm(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Film>((Film)null,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping("/regista/{regista}")
	@ApiOperation (value = "trova un lista di film", notes = "permette di trpvare una lista di film tramite un regista")
	public ResponseEntity<List<Film>> getFilmByRegista(@PathVariable String regista){
		try {
			return new ResponseEntity<List<Film>>(fd.getFilmByRegista(regista), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Film>>((List<Film>)null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
