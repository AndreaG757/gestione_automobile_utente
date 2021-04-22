package it.prova.gestioneautomobileutente.service.automobile;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneautomobileutente.dao.automobile.AutomobileDAO;
import it.prova.gestioneautomobileutente.model.Automobile;
import it.prova.gestioneautomobileutente.web.listener.LocalEntityManagerFactoryListener;

public class AutomobileServiceImpl implements AutomobileService {
	private AutomobileDAO automobileDao;
	
	@Override
	public void setAutomobileDao(AutomobileDAO automobileDao) {
		this.automobileDao = automobileDao;		
	}

	@Override
	public List<Automobile> listAll() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			automobileDao.setEntityManager(entityManager);

			return automobileDao.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Automobile caricaSingoloElemento(Long idInput) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			automobileDao.setEntityManager(entityManager);

			return automobileDao.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Automobile input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			automobileDao.setEntityManager(entityManager);

			automobileDao.update(input);

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
	public void inserisciNuovo(Automobile input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			automobileDao.setEntityManager(entityManager);

			automobileDao.insert(input);

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
	public void rimuovi(Automobile input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {
			
			entityManager.getTransaction().begin();

			automobileDao.setEntityManager(entityManager);

			input = entityManager.merge(input);

			automobileDao.delete(input);				

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
	public List<Automobile> cercaPerEsempio(Automobile input) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {

			automobileDao.setEntityManager(entityManager);

			return automobileDao.findByExample(input);				
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		
	}

}
