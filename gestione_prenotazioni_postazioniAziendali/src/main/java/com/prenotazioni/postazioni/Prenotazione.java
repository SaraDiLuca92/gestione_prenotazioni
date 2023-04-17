package com.prenotazioni.postazioni;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "prenotazione")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "postazione_id", referencedColumnName = "id")
    private Postazione postazione;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "utente_id", referencedColumnName = "id")
    private Utente utente;
  
    @Temporal(TemporalType.DATE)
    private LocalDate dataPrenotazione;

    private int numOre;

    public Prenotazione(Postazione postazione2, Utente utente2, LocalDate dataPrenotazione, int numOre) {
        this.postazione = postazione2;
        this.utente = utente2;
        this.dataPrenotazione = dataPrenotazione;
        this.numOre = numOre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Postazione getPostazione() {
        return postazione;
    }

    public void setPostazione(Postazione postazione) {
        this.postazione = postazione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public int getNumOre() {
        return numOre;
    }

    public void setNumOre(int numOre) {
        this.numOre = numOre;
    }
}
