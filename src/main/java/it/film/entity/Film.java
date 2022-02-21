package it.film.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery (name = "trova_tutti", query = "select f from Film f")

public class Film {
	//attributi
	private int id;
	private String titolo;
	private String anno;
	private String regista;
	private String tipo;
	private String incasso;
	
	//getters
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}
	@Column(name="titolo")
	public String getTitolo() {
		return titolo;
	}
	@Column(name="anno")
	public String getAnno() {
		return anno;
	}
	@Column(name="regista")
	public String getRegista() {
		return regista;
	}
	@Column(name="tipo")
	public String getTipo() {
		return tipo;
	}
	@Column(name="incasso")
	public String getIncasso() {
		return incasso;
	}
	
	//setters
	public void setId(int id) 				{this.id = id;}
	public void setTitolo(String titolo) 	{this.titolo = titolo;}
	public void setAnno(String anno) 		{this.anno = anno;}
	public void setRegista(String regista) 	{this.regista = regista;}
	public void setTipo(String tipo) 		{this.tipo = tipo;}
	public void setIncasso(String incasso) 	{this.incasso = incasso;}
	
	

}
