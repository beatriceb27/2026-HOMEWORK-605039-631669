package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;

public class ComandoPosa implements Comando{
	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		if (nomeAttrezzo == null) {
	    	this.io.mostraMessaggio("Cosa vuoi posare?");
	        return;
	    }

	    // 2. Rimuovo l'attrezzo dalla borsa (il tuo removeAttrezzo restituisce l'oggetto)
	    Attrezzo attrezzoDaPosare = partita.togliDallaBorsa(nomeAttrezzo);

	    // 3. Se l'attrezzo era effettivamente nella borsa...
	    if (attrezzoDaPosare != null) {
	        // Lo aggiungo alla stanza corrente
	        partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
	        this.io.mostraMessaggio("Hai posato: " + nomeAttrezzo);
	    }else {
	    	this.io.mostraMessaggio("Non hai questo attrezzo nella borsa.");
	    }

	}
	
	@Override
    public void setParametro(String parametro) {
    	this.nomeAttrezzo = parametro;
	}
	@Override
    public String getNome() {
		return "Posa";
	}
	
	@Override
    public String getParametro() {
		return this.nomeAttrezzo;
	}
	
	@Override
    public void setIo(IO io) {
		this.io = io;
	}
    
	
}
