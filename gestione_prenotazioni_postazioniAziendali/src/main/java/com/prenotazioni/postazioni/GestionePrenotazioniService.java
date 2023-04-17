package com.prenotazioni.postazioni;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionePrenotazioniService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public boolean prenotaPostazione(String codicePostazione, String username, LocalDate dataPrenotazione) {
        Postazione postazione = postazioneRepository.findByCodice(codicePostazione);
        Utente utente = utenteRepository.findByUsername(username);
        if (postazione == null || utente == null) {
            return false;
        }
        Prenotazione prenotazione = prenotazioneRepository.findByPostazioneAndDataPrenotazione(postazione, dataPrenotazione);
        if (prenotazione != null) {
            return false;
        }
        prenotazione = new Prenotazione(postazione, utente, dataPrenotazione, 0);
        prenotazione.setPostazione(postazione);
        prenotazione.setUtente(utente);
        prenotazione.setDataPrenotazione(dataPrenotazione);
        prenotazioneRepository.save(prenotazione);
        return true;
    }

    public void gestisciPrenotazioneConsole() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserire il proprio username: ");
        String username = scanner.nextLine();
        System.out.print("Inserire il codice della postazione: ");
        String codicePostazione = scanner.nextLine();
        System.out.print("Inserire la data della prenotazione (yyyy-MM-dd): ");
        String dataPrenotazioneString = scanner.nextLine();
        LocalDate dataPrenotazione = LocalDate.parse(dataPrenotazioneString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        boolean prenotazioneEffettuata = prenotaPostazione(codicePostazione, username, dataPrenotazione);
        if (prenotazioneEffettuata) {
            System.out.println("Prenotazione effettuata con successo.");
        } else {
            System.out.println("Errore durante la prenotazione.");
        } scanner.close();
    }

    public List<Postazione> cercaPostazioni(String tipo, String citta) {
        return postazioneRepository.findByTipoAndEdificioCitta(tipo, citta);
    }

    public void aggiungiPrenotazione(Prenotazione prenotazione) {
        prenotazioneRepository.save(prenotazione);
    }



}
