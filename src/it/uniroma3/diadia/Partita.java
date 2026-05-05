package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	
	public Partita(){
		this.giocatore = new Giocatore();
		this.labirinto = new Labirinto();
		this.labirinto.creaStanze();
		this.finita = false;
	}
	public Stanza getStanzaCorrente() {
	    return this.labirinto.getStanzaCorrente();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
	    this.labirinto.setStanzaCorrente(stanzaCorrente);
	}
	
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente().getNome().equals(this.labirinto.getStanzaVincente().getNome());
	}

	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}	
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	public boolean mettiInBorsa(Attrezzo attrezzo) {
		return this.giocatore.getBorsa().addAttrezzo(attrezzo);
	}
	public Attrezzo togliDallaBorsa(String nomeAttrezzo) {
	    return this.giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
	}
}