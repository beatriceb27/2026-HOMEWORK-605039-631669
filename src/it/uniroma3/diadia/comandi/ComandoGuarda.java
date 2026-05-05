package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
    private IO io;

    @Override
    public void esegui(Partita partita) {
        // Stampa la stanza corrente
        this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
        
        // Stampa lo stato del giocatore (CFU e Borsa)
        this.io.mostraMessaggio("Hai " + partita.getCfu() + " CFU.");
        this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
    }

    @Override
    public void setParametro(String parametro) {
        // Vuoto, non ha parametri
    }

    @Override
    public String getNome() {
        return "guarda";
    }

    @Override
    public String getParametro() {
        return null;
    }

    public void setIo(IO io) {
        this.io = io;
    }
}