package com.prenotazioni.postazioni;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "postazione")
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codice;

    private String descrizione;

    
    private String tipo;

    private int maxOccupanti;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "edificio_id", referencedColumnName = "id")
    private Edificio edificio;

	public Postazione(String tipo2, String citta) {
		this.tipo=tipo2; this.descrizione=citta;;
	}

	public Postazione(String codicePostazione, String descrizionePostazione, String tipoPostazione, int numMaxOccupanti,
			Edificio edificio2) {
this.codice=codicePostazione;
this.descrizione=descrizionePostazione;
this.tipo=tipoPostazione;
this.maxOccupanti=numMaxOccupanti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getMaxOccupanti() {
		return maxOccupanti;
	}

	public void setMaxOccupanti(int maxOccupanti) {
		this.maxOccupanti = maxOccupanti;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

    
}
