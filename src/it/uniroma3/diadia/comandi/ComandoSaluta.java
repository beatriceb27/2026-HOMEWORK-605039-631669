package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

    private static final String MESSAGGIO_CHI = "Chi dovrei salutare? Qui non c'e' nessuno!";

    @Override
    public void esegui(Partita partita) {
        AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
        if (personaggio != null) {
            this.getIo().mostraMessaggio(personaggio.saluta());
        } else {
            this.getIo().mostraMessaggio(MESSAGGIO_CHI);
        }
    }

    @Override
    public String getNome() {
        return "saluta";
    }
}