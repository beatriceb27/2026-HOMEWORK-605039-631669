package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class DiaDiaTest {

    @Test
    public void testPartita_ComandoFine() {
        String[] comandiDaEseguire = {"fine"};
        
        IOsimulator io = new IOsimulator(comandiDaEseguire);
        DiaDia gioco = new DiaDia(io);
        
        gioco.gioca();
        
        assertTrue(io.hasNextMessaggio());
        
        String ultimoMessaggio = "";
        while(io.hasNextMessaggio()) {
            ultimoMessaggio = io.nextMessaggio();
        }
        
        assertTrue(ultimoMessaggio.contains("Grazie di aver giocato"));
    }

    @Test
    public void testPartita_Esplorazione() {
        String[] comandiDaEseguire = {"vai nord", "guarda", "fine"};
        
        IOsimulator io = new IOsimulator(comandiDaEseguire);
        DiaDia gioco = new DiaDia(io);
        
        gioco.gioca();
        
        boolean haVistoStanza = false;
        
        while(io.hasNextMessaggio()) {
            String messaggio = io.nextMessaggio();
            if (messaggio != null && messaggio.contains("biblioteca")) {
                haVistoStanza = true;
            }
        }
        
        assertTrue(haVistoStanza, "Il giocatore non ha visto la stanza a nord");
    }

    @Test
    public void testPartita_EsplorazioneCompleta() {
        String[] comandiDaEseguire = {
            "guarda",            
            "prendi osso",       
            "vai est",           
            "guarda",            
            "posa osso",         
            "vai nord",          
            "comandoinventato",  
            "fine"               
        };
        
        IOsimulator io = new IOsimulator(comandiDaEseguire);
        DiaDia gioco = new DiaDia(io);
        
        gioco.gioca();
        
        StringBuilder outputCompleto = new StringBuilder();
        while(io.hasNextMessaggio()) {
            outputCompleto.append(io.nextMessaggio()).append("\n");
        }
        String logPartita = outputCompleto.toString();
        
        assertTrue(logPartita.contains("Atrio"), "Il giocatore non ha visto l'Atrio");
        assertTrue(logPartita.contains("Aula N11"), "Il giocatore non è arrivato nell'Aula N11");
        assertTrue(logPartita.contains("valido") || logPartita.contains("sconosciuto"), "Non ha segnalato l'errore sul comando inventato");
    }

    @Test
    public void testPartita_Vittoria() {
        String[] comandiDaEseguire = {"vai nord", "vai est", "fine"}; 
        
        IOsimulator io = new IOsimulator(comandiDaEseguire);
        DiaDia gioco = new DiaDia(io);
        
        gioco.gioca();
        
        StringBuilder outputCompleto = new StringBuilder();
        while(io.hasNextMessaggio()) {
            outputCompleto.append(io.nextMessaggio()).append("\n");
        }
        String logPartita = outputCompleto.toString();
        
        assertTrue(logPartita.contains("Hai vinto!"));
    }

    @Test
    public void testPartita_EsaurimentoCFU() {
        String[] comandiDaEseguire = new String[22];
        
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                comandiDaEseguire[i] = "vai est";
            } else {
                comandiDaEseguire[i] = "vai ovest";
            }
        }
        comandiDaEseguire[20] = "guarda"; 
        comandiDaEseguire[21] = "fine";
        
        IOsimulator io = new IOsimulator(comandiDaEseguire);
        DiaDia gioco = new DiaDia(io);
        
        gioco.gioca();
        
        StringBuilder outputCompleto = new StringBuilder();
        while(io.hasNextMessaggio()) {
            outputCompleto.append(io.nextMessaggio()).append("\n");
        }
        String logPartita = outputCompleto.toString();
        
        assertTrue(logPartita.contains("Hai esaurito i CFU..."));
    }
    @Test
    public void testPartitaVinta_Bilocale() {
    	Labirinto bilocale = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanzaVincente("Biblioteca")
                .addAdiacenza("Atrio", "Biblioteca", "nord")
                .getLabirinto();
        
        IOsimulator io = new IOsimulator("vai nord");
        
        DiaDia gioco = new DiaDia(bilocale, io);
        gioco.gioca();
        
        boolean haVinto = false;
        while (io.hasNextMessaggio()) {
            String messaggio = io.nextMessaggio();
            if (messaggio != null && (messaggio.contains("vinto") || messaggio.contains("Vittoria"))) {
                haVinto = true;
            }
        }
        
        assertTrue(haVinto, "La partita non è stata vinta spostandosi a nord!");
    }
}