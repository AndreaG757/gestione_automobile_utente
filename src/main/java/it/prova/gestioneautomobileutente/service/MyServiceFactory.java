package it.prova.gestioneautomobileutente.service;

import it.prova.gestioneautomobileutente.dao.automobile.AutomobileDAO;
import it.prova.gestioneautomobileutente.dao.automobile.AutomobileDAOImpl;
import it.prova.gestioneautomobileutente.dao.utente.UtenteDAO;
import it.prova.gestioneautomobileutente.dao.utente.UtenteDAOImpl;
import it.prova.gestioneautomobileutente.service.automobile.AutomobileService;
import it.prova.gestioneautomobileutente.service.automobile.AutomobileServiceImpl;
import it.prova.gestioneautomobileutente.service.utente.UtenteService;
import it.prova.gestioneautomobileutente.service.utente.UtenteServiceImpl;

public class MyServiceFactory {

	// implementiamo il singleton in modo da evitare
	// proliferazione di riferimenti
	private static AutomobileService AUTOMOBILE_SERVICE_INSTANCE = null;
	private static UtenteService UTENTE_SERVICE_INSTANCE = null;

	private static AutomobileDAO AUTOMOBILEDAO_INSTANCE = null;
	private static UtenteDAO UTENTEDAO_INSTANCE = null;

	public static AutomobileService getAutomobileServiceInstance() {
		if (AUTOMOBILE_SERVICE_INSTANCE == null)
			AUTOMOBILE_SERVICE_INSTANCE = new AutomobileServiceImpl();

		if (AUTOMOBILEDAO_INSTANCE == null)
			AUTOMOBILEDAO_INSTANCE = new AutomobileDAOImpl();

		AUTOMOBILE_SERVICE_INSTANCE.setAutomobileDao(AUTOMOBILEDAO_INSTANCE);

		return AUTOMOBILE_SERVICE_INSTANCE;
	}
	
	public static UtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTEDAO_INSTANCE == null)
			UTENTEDAO_INSTANCE = new UtenteDAOImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDao(UTENTEDAO_INSTANCE);

		return UTENTE_SERVICE_INSTANCE;
	}

}
