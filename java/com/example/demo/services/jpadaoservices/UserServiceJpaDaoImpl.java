package com.example.demo.services.jpadaoservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.services.crudservices.UserService;
import com.example.demo.services.security.EncryptionService;

@Service
@Profile("jpaDao")
public class UserServiceJpaDaoImpl implements UserService {

	private EncryptionService encryptionService;

	private EntityManagerFactory emf;

	public UserServiceJpaDaoImpl() {
		super();
	}

	

	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<User> listAllUser() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery(" from User ", User.class).getResultList();
	}

	@Override
	public User getUserById(Integer id) {
		return emf.createEntityManager().find(User.class, id);
	}

	@Override
	public User saveOrUpdateUser(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		if (null != user && null != user.getPassword()) {
			user.setEncryptedpassword(encryptionService.encryptString(user.getPassword()));
		}

		User savedUser = em.merge(user);
		
		if (null != savedUser) {
			em.getTransaction().commit();
		} else {
			em.getTransaction().rollback();
		}
		return savedUser;
	}

	@Override
	public void deleteUser(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(User.class, id));
		em.getTransaction().commit();

	}

	@Autowired
	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	public EncryptionService getEncryptionService() {
		return encryptionService;
	}
}
