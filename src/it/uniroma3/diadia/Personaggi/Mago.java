package it.uniroma3.diadia.Personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
    
    private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
            "con una mia magica azione, troverai un nuovo oggetto " +
            "per il tuo borsone!";
    private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
    
    private Attrezzo attrezzo;

    public Mago(String nome, String presentazione, Attrezzo attrezzo) {
        super(nome, presentazione);
        this.attrezzo = attrezzo;
    }
    
    @Override
    public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
        Attrezzo attrezzoModificato = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso() / 2);
        partita.getStanzaCorrente().addAttrezzo(attrezzoModificato);
        return "Mumble mumble... Abrakadabra! Ho dimezzato il peso di " + attrezzo.getNome() + " e l'ho lasciato a terra!";
    }

    @Override
    public String interagisci(Partita partita) {
        String risposta;
        if (this.attrezzo != null) {
            partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
            this.attrezzo = null;
            risposta = MESSAGGIO_DONO;
        } else {
            risposta = MESSAGGIO_SCUSE;
        }
        return risposta;
    }
}