package it.prova.gestioneautomobileutente.service.utente;

import java.util.List;

import it.prova.gestioneautomobileutente.dao.utente.UtenteDAO;
import it.prova.gestioneautomobileutente.model.Utente;

public interface UtenteService {
	public void setUtenteDao(UtenteDAO libroDao);

	public List<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Utente input) throws Exception;

	public void inserisciNuovo(Utente input) throws Exception;

	public void rimuovi(Utente input) throws Exception;
	
	public Utente findByUsernameAndPassword(String username,String password) throws Exception;
}
