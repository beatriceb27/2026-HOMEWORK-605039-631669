package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;

public class ComandoPosa extends AbstractComando{
	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		if (nomeAttrezzo == null) {
	    	this.io.mostraMessaggio("Cosa vuoi posare?");
	        return;
	    }
	    Attrezzo attrezzoDaPosare = partita.togliDallaBorsa(nomeAttrezzo);
	    if (attrezzoDaPosare != null) {
	        partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
	        this.io.mostraMessaggio("Hai posato: " + nomeAttrezzo);
	    }else {
	    	this.io.mostraMessaggio("Non hai questo attrezzo nella borsa.");
	    }

	}
	
	
	@Override
    public String getNome() {
		return "Posa";
	}
}
