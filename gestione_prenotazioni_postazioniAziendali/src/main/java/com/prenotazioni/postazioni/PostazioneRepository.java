package com.prenotazioni.postazioni;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    List<Postazione> findByTipoAndEdificioCitta(String tipo, String citta);
    
    @Query("SELECT p FROM Postazione p WHERE NOT EXISTS (SELECT pr FROM Prenotazione pr WHERE pr.postazione = p AND pr.dataPrenotazione = :data)")
    List<Postazione> getPostazioniDisponibili(@Param("data") Date data);
    Postazione findByCodice(String codice);
    
}

