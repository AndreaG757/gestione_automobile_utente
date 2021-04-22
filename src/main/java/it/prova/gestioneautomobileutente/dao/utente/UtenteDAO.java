package it.prova.gestioneautomobileutente.dao.utente;

import it.prova.gestioneautomobileutente.dao.IBaseDAO;
import it.prova.gestioneautomobileutente.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{
	public Utente findByUsernameAndPassword(String username, String password) throws Exception;
}
