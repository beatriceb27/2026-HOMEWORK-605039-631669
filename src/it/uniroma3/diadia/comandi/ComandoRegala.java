package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

    @Override
    public void esegui(Partita partita) {
        AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
        
        if (personaggio == null) {
            this.getIo().mostraMessaggio("Non c'e' nessuno a cui fare un regalo in questa stanza!");
            return;
        }

        String nomeAttrezzo = this.getParametro();
        if (nomeAttrezzo == null) {
            this.getIo().mostraMessaggio("Cosa vuoi regalare? Specifica un attrezzo.");
            return;
        }

        Attrezzo attrezzoDaRegalare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
        if (attrezzoDaRegalare == null) {
            this.getIo().mostraMessaggio("Non hai un attrezzo di nome " + nomeAttrezzo + " nella tua borsa!");
            return;
        }

        partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
        String risposta = personaggio.riceviRegalo(attrezzoDaRegalare, partita);
        
        this.getIo().mostraMessaggio(risposta);
    }

    @Override
    public String getNome() {
        return "regala";
    }
}