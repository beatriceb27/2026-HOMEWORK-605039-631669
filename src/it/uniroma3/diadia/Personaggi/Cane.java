package it.uniroma3.diadia.Personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

    private static final String MESSAGGIO_MORSO = "Grrr... Argh! Il cane ti ha morso! Hai perso 1 CFU.";
    private static final String MESSAGGIO_REGALO_SBAGLIATO = "Grrr... Non e' quello che voglio! Il cane ti morde! Hai perso 1 CFU.";
    private static final String MESSAGGIO_REGALO_GIUSTO = "Arf arf! Il cane mangia felice e lascia cadere qualcosa a terra.";
    
    private String ciboPreferito;
    private Attrezzo attrezzo; 
    public Cane(String nome, String presentazione, Attrezzo attrezzo) {
        super(nome, presentazione);
        this.ciboPreferito = "osso";
        this.attrezzo = attrezzo;
    }

    @Override
    public String interagisci(Partita partita) {
        int cfuAttuali = partita.getGiocatore().getCfu();
        partita.getGiocatore().setCfu(cfuAttuali - 1);
        return MESSAGGIO_MORSO;
    }

    @Override
    public String riceviRegalo(Attrezzo regalo, Partita partita) {
        if (regalo.getNome().equals(this.ciboPreferito)) {
            if (this.attrezzo != null) {
                partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
                this.attrezzo = null;
            }
            return MESSAGGIO_REGALO_GIUSTO;
        } else {
            int cfuAttuali = partita.getGiocatore().getCfu();
            partita.getGiocatore().setCfu(cfuAttuali - 1);
            return MESSAGGIO_REGALO_SBAGLIATO;
        }
    }
}