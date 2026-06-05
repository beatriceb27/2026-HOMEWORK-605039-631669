package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;

public class ComandoPrendi extends AbstractComando{
	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		 if (nomeAttrezzo == null) {
		    	this.io.mostraMessaggio("Cosa vuoi prendere?");
		        return; 
		    }
		    Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);

		    if (attrezzoDaPrendere != null) {
		    	boolean aggiunto = partita.mettiInBorsa(attrezzoDaPrendere);
		        
		        if (aggiunto) {
		            partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
		            this.io.mostraMessaggio("Hai preso: " + nomeAttrezzo);
		        } else {
		        	this.io.mostraMessaggio("La borsa è piena o l'attrezzo è troppo pesante!");
		        }
		    } else {
		    	this.io.mostraMessaggio("Attrezzo inesistente in questa stanza.");
		    }
		}
	@Override
    public String getNome() {
		return "Prendi";
	}
	
	
    
	
}
