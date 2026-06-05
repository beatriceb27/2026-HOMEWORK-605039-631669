package it.uniroma3.diadia.Personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

    private static final String MESSAGGIO_SALUTATA = "Solo perche' sei stato educato, ti trasferisco in una stanza piena di oggetti!";
    private static final String MESSAGGIO_NON_SALUTATA = "Sei un gran maleducato! Ti sbatto in uno sgabuzzino vuoto!";

    public Strega(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
    public String interagisci(Partita partita) {
        String messaggio;
        Stanza stanzaCorrente = partita.getStanzaCorrente();
        Stanza stanzaDestinazione = null;

        if (stanzaCorrente.getDirezioni().isEmpty()) {
            return "Volevo farti una magia, ma qui non ci sono porte!";
        }

        if (this.haSalutato()) {
            messaggio = MESSAGGIO_SALUTATA;
            int maxAttrezzi = -1;

            for (Direzione dir : stanzaCorrente.getDirezioni()) {
                Stanza s = stanzaCorrente.getStanzaAdiacente(dir);
                if (s != null && s.getAttrezzi().size() > maxAttrezzi) {
                    maxAttrezzi = s.getAttrezzi().size();
                    stanzaDestinazione = s;
                }
            }
        } else {
            messaggio = MESSAGGIO_NON_SALUTATA;
            int minAttrezzi = 9999;

            for (Direzione dir : stanzaCorrente.getDirezioni()) {
                Stanza s = stanzaCorrente.getStanzaAdiacente(dir);
                if (s != null && s.getAttrezzi().size() < minAttrezzi) {
                    minAttrezzi = s.getAttrezzi().size();
                    stanzaDestinazione = s;
                }
            }
        }

        if (stanzaDestinazione != null) {
            partita.setStanzaCorrente(stanzaDestinazione);
        }

        return messaggio;
    }

    @Override
    public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
        return "AHAHAH! Grazie per il regalo, stolto! Questo " + attrezzo.getNome() + " ora e' mio e non lo rivedrai mai piu'!";
    }
}