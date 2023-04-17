package com.prenotazioni.postazioni;

import java.time.LocalDate;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
	 Prenotazione findByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate dataPrenotazione);

}
