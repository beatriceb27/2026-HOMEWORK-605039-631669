package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;

public class ComandoPrendi implements Comando{
	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		 if (nomeAttrezzo == null) {
		    	this.io.mostraMessaggio("Cosa vuoi prendere?");
		        return; // Interrompiamo il metodo
		    }

		    // 2. Cerco l'attrezzo nella stanza corrente
		    Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);

		    // 3. Se l'attrezzo esiste nella stanza...
		    if (attrezzoDaPrendere != null) {
		        // Provo a metterlo in borsa
		    	boolean aggiunto = partita.mettiInBorsa(attrezzoDaPrendere);
		        
		        if (aggiunto) {
		            // Se c'è spazio in borsa, lo rimuovo fisicamente dalla stanza
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
    public void setParametro(String parametro) {
    	this.nomeAttrezzo = parametro;
	}
	@Override
    public String getNome() {
		return "Prendi";
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
