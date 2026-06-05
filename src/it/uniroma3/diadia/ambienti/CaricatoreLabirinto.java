package it.uniroma3.diadia.ambienti;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

    private static final String STANZE_MARKER = "Stanze:";
    private static final String ESTREMI_MARKER = "Estremi:";
    private static final String ATTREZZI_MARKER = "Attrezzi:";
    private static final String USCITE_MARKER = "Uscite:";

    private String nomeFile;
    private Labirinto.LabirintoBuilder builder;
    private boolean inizialeLetta = false;

    public CaricatoreLabirinto(String nomeFile) {
        this.nomeFile = nomeFile;
        this.builder = Labirinto.newBuilder();
    }

    public void carica() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String riga;
            String sezioneAttuale = "";
            
            while ((riga = reader.readLine()) != null) {
                riga = riga.trim();
                if (riga.isEmpty()) continue;

                if (riga.equals(STANZE_MARKER) || riga.equals(ESTREMI_MARKER) || 
                    riga.equals(ATTREZZI_MARKER) || riga.equals(USCITE_MARKER)) {
                    sezioneAttuale = riga;
                    continue;
                }

                try (Scanner scannerDiLinea = new Scanner(riga)) {
                    switch (sezioneAttuale) {
                        case STANZE_MARKER:
                            this.builder.addStanza(riga);
                            break;
                            
                        case ESTREMI_MARKER:
                            if (!inizialeLetta) {
                                Stanza iniziale = this.builder.getListaStanze().get(riga);
                                this.builder.getLabirinto().setStanzaIniziale(iniziale);
                                inizialeLetta = true;
                            } else {
                                Stanza vincente = this.builder.getListaStanze().get(riga);
                                this.builder.getLabirinto().setStanzaVincente(vincente);
                            }
                            break;
                            
                        case ATTREZZI_MARKER:
                            String nomeA = scannerDiLinea.next();
                            int pesoA = scannerDiLinea.nextInt();
                            String nomeStanzaA = scannerDiLinea.next();
                            
                            Stanza s = this.builder.getListaStanze().get(nomeStanzaA);
                            if (s != null) {
                                s.addAttrezzo(new Attrezzo(nomeA, pesoA));
                            }
                            break;
                            
                        case USCITE_MARKER:
                            String stanza1 = scannerDiLinea.next();
                            String dir = scannerDiLinea.next();
                            String stanza2 = scannerDiLinea.next();
                            this.builder.addAdiacenza(stanza1, stanza2, dir);
                            break;
                    }
                }
            }
        }
    }

    public Labirinto getLabirinto() {
        return this.builder.getLabirinto();
    }
}