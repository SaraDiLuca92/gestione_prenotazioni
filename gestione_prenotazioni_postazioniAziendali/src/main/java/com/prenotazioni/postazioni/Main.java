package com.prenotazioni.postazioni;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
	    ApplicationContext context = SpringApplication.run(Main.class, args);

	    GestionePrenotazioniService gestionePrenotazioniService = context.getBean(GestionePrenotazioniService.class);
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Benvenuto nella gestione delle prenotazioni!");

	    boolean continua = true;
	    while (continua) {
	        System.out.println("\nCosa vuoi fare?");
	        System.out.println("1. Effettuare una prenotazione");
	        System.out.println("2. Visualizzare le postazioni disponibili");
	        System.out.println("3. Esci");

	        String scelta = null;
	        try {
	            scelta = scanner.nextLine();
	        } catch (NoSuchElementException e) {
	            System.out.println("Errore: input non valido.");
	            continue;
	        }

	        switch (scelta) {
	        case "1":
	            System.out.print("Inserire il codice univoco della postazione: ");
	            String codicePostazione = scanner.nextLine();
	            System.out.print("Inserire la descrizione della postazione: ");
	            String descrizionePostazione = scanner.nextLine();
	            System.out.print("Inserire il tipo di postazione [ premi 1:PRIVATO, premi 2:OPENSPACE, premi 3:SALA RIUNIONI]: ");
	            String tipoPostazione = scanner.nextLine();
	            System.out.print("Inserire il numero massimo di occupanti della postazione: ");
	            int numMaxOccupanti = scanner.nextInt();
	            scanner.nextLine(); // consuma il carattere di fine linea
	            System.out.print("Inserire il nome dell'edificio: ");
	            String nomeEdificio = scanner.nextLine();
	            System.out.print("Inserire l'indirizzo dell'edificio: ");
	            String indirizzoEdificio = scanner.nextLine();
	            System.out.print("Inserire la città dell'edificio: ");
	            String cittaEdificio = scanner.nextLine();
	            Edificio edificio = new Edificio(nomeEdificio, indirizzoEdificio, cittaEdificio);
	            Postazione postazione = new Postazione(codicePostazione, descrizionePostazione, tipoPostazione, numMaxOccupanti, edificio);
	            Utente utente = new Utente(); // create a new Utente object as needed
	            System.out.print("Inserire il tuo username: ");
	            String username = scanner.nextLine();
	            System.out.print("Inserire il tuo nome completo: ");
	            String nomeCompleto = scanner.nextLine();
	            System.out.print("Inserire la tua email: ");
	            String email = scanner.nextLine();
	            utente.setUsername(username);
	            utente.setNomeCompleto(nomeCompleto);
	            utente.setEmail(email);
	            System.out.print("Inserire la data della prenotazione [yyyy/MM/dd]: ");
	            String dataPrenotazioneStr = scanner.nextLine();
	            LocalDate dataPrenotazione = LocalDate.parse(dataPrenotazioneStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                System.out.print("Inserire il numero di ore di prenotazione: ");
                int numOre = scanner.nextInt();
                scanner.nextLine();
                Prenotazione prenotazione = new Prenotazione(postazione, utente, dataPrenotazione, numOre);
                gestionePrenotazioniService.aggiungiPrenotazione(prenotazione);
                break;

            case "2":
                System.out.print("Inserire il tipo di postazione desiderato [PRIVATO, OPENSPACE, SALA RIUNIONI]: ");
                String tipoPostazione1 = scanner.nextLine();
                System.out.print("Inserire la città di interesse: ");
                String citta1 = scanner.nextLine();
                List<Postazione> postazioniDisponibili = gestionePrenotazioniService.cercaPostazioni(tipoPostazione1, citta1);
                System.out.println("\nPostazioni disponibili:");
                for (Postazione postazione1 : postazioniDisponibili) {
                    System.out.println(postazione1);
                }
                break;

            case "3":
                System.out.println("Grazie per aver usato il nostro servizio!");
                continua = false;
                break;

            default:
                System.out.println("Scelta non valida, riprovare.");
            }
        }

        scanner.close();
    }

}
