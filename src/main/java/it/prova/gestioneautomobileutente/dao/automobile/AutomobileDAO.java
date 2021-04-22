package it.prova.gestioneautomobileutente.dao.automobile;

import java.util.List;

import it.prova.gestioneautomobileutente.dao.IBaseDAO;
import it.prova.gestioneautomobileutente.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile> {

	public List<Automobile> findByExample(Automobile automobileInput) throws Exception;
	
}
