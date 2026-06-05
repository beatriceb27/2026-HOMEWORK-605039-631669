package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	@Override
	public void esegui(Partita partita) {
        StringBuilder risultato = new StringBuilder("Comandi disponibili: ");
        
        for (String comando : AbstractComando.getComandiDisponibili()) {
            risultato.append(comando).append(" ");
        }
        
        this.getIo().mostraMessaggio(risultato.toString());
    }
	
	@Override
    public String getNome() {
		return "Aiuto";
	}
}
