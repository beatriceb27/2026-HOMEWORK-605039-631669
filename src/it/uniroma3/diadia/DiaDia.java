package it.uniroma3.diadia;


import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {
	private IOConsole console;

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","posa", "prendi"};

	private Partita partita;

	public DiaDia(IOConsole console) { 
        this.partita = new Partita();
        this.console = console;
	}

	public void gioca() {	
		String istruzione;
		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   

	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else
			System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}   

	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}


	private void vai(String direzione) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu-1);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}
	
	private void prendi(String nomeAttrezzo) {
	    // 1. Controllo se l'utente ha scritto solo "prendi" senza specificare cosa
	    if (nomeAttrezzo == null) {
	        System.out.println("Cosa vuoi prendere?");
	        return; // Interrompiamo il metodo
	    }

	    // 2. Cerco l'attrezzo nella stanza corrente
	    Attrezzo attrezzoDaPrendere = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);

	    // 3. Se l'attrezzo esiste nella stanza...
	    if (attrezzoDaPrendere != null) {
	        // Provo a metterlo in borsa
	    	boolean aggiunto = this.partita.mettiInBorsa(attrezzoDaPrendere);
	        
	        if (aggiunto) {
	            // Se c'è spazio in borsa, lo rimuovo fisicamente dalla stanza
	            this.partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
	            System.out.println("Hai preso: " + nomeAttrezzo);
	        } else {
	            System.out.println("La borsa è piena o l'attrezzo è troppo pesante!");
	        }
	    } else {
	        System.out.println("Attrezzo inesistente in questa stanza.");
	    }
	}

	private void posa(String nomeAttrezzo) {
	    // 1. Controllo se l'utente ha scritto solo "posa" senza specificare cosa
	    if (nomeAttrezzo == null) {
	        System.out.println("Cosa vuoi posare?");
	        return;
	    }

	    // 2. Rimuovo l'attrezzo dalla borsa (il tuo removeAttrezzo restituisce l'oggetto)
	    Attrezzo attrezzoDaPosare = this.partita.togliDallaBorsa(nomeAttrezzo);

	    // 3. Se l'attrezzo era effettivamente nella borsa...
	    if (attrezzoDaPosare != null) {
	        // Lo aggiungo alla stanza corrente
	        this.partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
	        System.out.println("Hai posato: " + nomeAttrezzo);
	    } else {
	        System.out.println("Non hai questo attrezzo nella borsa.");
	    }
	}
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		    IOConsole console = new IOConsole();
		    DiaDia gioco = new DiaDia(console); 	    
		    gioco.gioca();
		}
}
