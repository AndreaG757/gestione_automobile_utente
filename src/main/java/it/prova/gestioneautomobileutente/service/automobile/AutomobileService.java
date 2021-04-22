package it.prova.gestioneautomobileutente.service.automobile;

import java.util.List;

import it.prova.gestioneautomobileutente.dao.automobile.AutomobileDAO;
import it.prova.gestioneautomobileutente.model.Automobile;


public interface AutomobileService {
	public void setAutomobileDao(AutomobileDAO automobileDao);

	public List<Automobile> listAll() throws Exception;

	public Automobile caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Automobile input) throws Exception;

	public void inserisciNuovo(Automobile input) throws Exception;

	public void rimuovi(Automobile input) throws Exception;
	
	public List<Automobile> cercaPerEsempio(Automobile input) throws Exception;
	
}
