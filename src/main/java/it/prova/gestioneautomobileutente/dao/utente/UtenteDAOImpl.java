package it.prova.gestioneautomobileutente.dao.utente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneautomobileutente.model.Utente;

public class UtenteDAOImpl implements UtenteDAO {
	private EntityManager entityManager;

	@Override
	public List<Utente> list() throws Exception {
		return entityManager.createQuery("from Utente", Utente.class).getResultList();
	}

	@Override
	public Utente findOne(Long id) throws Exception {
		return entityManager.find(Utente.class, id);
	}

	@Override
	public void update(Utente input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		
		input = entityManager.merge(input);			
	}

	@Override
	public void insert(Utente input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		
		entityManager.persist(input);
	}

	@Override
	public void delete(Utente input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	@Override
	public Utente findByUsernameAndPassword(String username, String password) throws Exception {
		 TypedQuery<Utente> query = entityManager.createQuery("select u FROM Utente u where u.password = :password and u.username = :username", Utente.class);
	        query.setParameter("username", username);
	        query.setParameter("password", password);
	        return query.getSingleResult();
	}

}
