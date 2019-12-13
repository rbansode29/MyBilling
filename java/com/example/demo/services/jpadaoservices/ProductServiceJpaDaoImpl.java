package com.example.demo.services.jpadaoservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.services.crudservices.ProductService;

@Service
@Profile("jpaDao")
public class ProductServiceJpaDaoImpl implements ProductService {
	private EntityManagerFactory emf;

	public ProductServiceJpaDaoImpl() {
		super();
	}

	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Product> listAllProduct() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery(" from Product ", Product.class).getResultList();
	}

	@Override
	public Product getProductById(Integer id) {
		return emf.createEntityManager().find(Product.class, id);
	}

	@Override
	public Product saveOrUpdateProduct(Product product) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Product savedProduct = null;
		savedProduct = em.merge(product);
		if (null != savedProduct) {
			em.getTransaction().commit();
		} else {
			em.getTransaction().rollback();
		}
		return savedProduct;
	}

	@Override
	public void deleteProduct(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Product.class, id));
	}

}
