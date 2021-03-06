package com.attikovacs.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.attikovacs.domain.Customer;

@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl implements CustomerService {

	private EntityManagerFactory emf;
	
	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Customer> listAll() {
		EntityManager em = emf.createEntityManager();
		
		return em.createQuery("from Customer", Customer.class).getResultList();
	}

	@Override
	public Customer getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		return em.find(Customer.class, id);
	}

	@Override
	public Customer saveOrUpdate(Customer domainObject) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Customer savedProduct = em.merge(domainObject);
		em.getTransaction().commit();
		
		return savedProduct;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		em.remove(em.find(Customer.class, id));
	}

}
