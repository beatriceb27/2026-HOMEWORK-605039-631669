package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{
    private IO io;

    @Override
    public void esegui(Partita partita) {
        this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());        
        this.io.mostraMessaggio("Hai " + partita.getCfu() + " CFU.");
        this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
    }

    @Override
    public String getNome() {
        return "guarda";
    }

}