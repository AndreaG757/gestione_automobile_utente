package it.prova.gestioneautomobileutente.service.utente;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneautomobileutente.dao.utente.UtenteDAO;
import it.prova.gestioneautomobileutente.model.Utente;
import it.prova.gestioneautomobileutente.web.listener.LocalEntityManagerFactoryListener;

public class UtenteServiceImpl implements UtenteService {
	private UtenteDAO utenteDao;
	
	@Override
	public void setUtenteDao(UtenteDAO utenteDao) {
		this.utenteDao = utenteDao;
	}

	@Override
	public List<Utente> listAll() throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			utenteDao.setEntityManager(entityManager);

			return utenteDao.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Utente caricaSingoloElemento(Long idInput) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			utenteDao.setEntityManager(entityManager);

			return utenteDao.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Utente input) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			utenteDao.setEntityManager(entityManager);
			
			utenteDao.update(input);

			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void inserisciNuovo(Utente input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			utenteDao.setEntityManager(entityManager);

			utenteDao.insert(input);

			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}		
		
	}

	@Override
	public void rimuovi(Utente input) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {
			
			entityManager.getTransaction().begin();

			utenteDao.setEntityManager(entityManager);

			input = entityManager.merge(input);

			utenteDao.delete(input);				

			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		
	}
	
	@Override
	public Utente findByUsernameAndPassword(String username,String password) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		Utente utenteTrovato = null;
		
		try {
			
			utenteDao.setEntityManager(entityManager);

			utenteTrovato = utenteDao.findByUsernameAndPassword(username, password);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		
		return utenteTrovato;
	}

}
