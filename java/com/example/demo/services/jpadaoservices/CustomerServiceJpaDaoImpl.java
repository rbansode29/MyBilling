package com.example.demo.services.jpadaoservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Customer;
import com.example.demo.services.crudservices.CustomerService;


@Service
@Profile("jpaDao")
public class CustomerServiceJpaDaoImpl implements CustomerService {
	private EntityManagerFactory emf;

	public CustomerServiceJpaDaoImpl() {
		super();
	}

	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Customer> listAllCustomer() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery(" from Customer ", Customer.class).getResultList();
	}

	@Override
	public Customer getCustomerById(Integer id) {
		// TODO Auto-generated method stub
		return emf.createEntityManager().find(Customer.class, id);
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Customer savedCustomer = null;
		savedCustomer = em.merge(customer);
		if (null != savedCustomer) {
			em.getTransaction().commit();
		} else {
			em.getTransaction().rollback();
		}
		return savedCustomer;
	}

	@Override
	public void deleteCustomer(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Customer.class, id));
		em.getTransaction().commit();
	}
}
