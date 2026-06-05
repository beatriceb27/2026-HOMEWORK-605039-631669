package it.uniroma3.diadia;


import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import java.util.Scanner;
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
	private Partita partita;
	private IO io;
	public DiaDia(IO io) {
        this.io = io;
        
        this.partita = new Partita(Labirinto.newBuilder().getLabirinto());
        }

    public DiaDia(Labirinto labirinto, IO io) {
        this.io = io;
        this.partita = new Partita(labirinto);
    }
    
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	public void gioca() {	
		String istruzione;
		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   

	private boolean processaIstruzione(String istruzione) {
	    Comando comandoDaEseguire;
	    
	    FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
	    comandoDaEseguire = factory.costruisciComando(istruzione); 
	    comandoDaEseguire.setIo(this.io); 
	    comandoDaEseguire.esegui(this.partita); 
	    
	    if (this.partita.vinta()) { 
	        this.io.mostraMessaggio("Hai vinto!"); 
	        return true;
	    }
	    if (this.partita.getGiocatore().getCfu() == 0) { 
	        this.io.mostraMessaggio("Hai esaurito i CFU..."); 
	        return true;
	    }
	    
	    return this.partita.isFinita();
	}   

public static void main(String[] argc) throws Exception {
       
        try (Scanner scanner = new Scanner(System.in)) {
            
            IO io = new IOConsole(scanner);
            
            CaricatoreLabirinto caricatore = new CaricatoreLabirinto("labirinto.txt");
            caricatore.carica();
            Labirinto labirinto = caricatore.getLabirinto();
            
            DiaDia gioco = new DiaDia(labirinto, io);
            gioco.gioca();
        }
    }
}
